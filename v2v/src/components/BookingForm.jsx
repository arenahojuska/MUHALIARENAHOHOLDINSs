import { useState } from 'react';
import { Download, User, Phone, MapPin, Calendar, Bus, Sparkles } from 'lucide-react';
import { generatePDF } from '../utils/pdfGenerator';

const BookingForm = ({ onBookingComplete }) => {
  const [formData, setFormData] = useState({
    fullName: '',
    cellphone: '',
    pickupPoint: '',
    dropoffLocation: '',
    travelDate: ''
  });

  const [isSubmitting, setIsSubmitting] = useState(false);

  const pickupPoints = [
    'Thohoyandou Thavhani Mall ',
    'Zwikwengani',
    'Tshakhuma',
    'Elim',
    'Nzhelele',
    'Louis Trichardt',
    'Polokwane',
    'Naboom'
  ];

  const travelDates = [
   
    '28 January 18:00pm',
    '29 January 06:00am',
    '3 February 17:00pm',
    '6 February 06:00am',
    '15 February 06:00am'
  ];

  const handleSubmit = async (e) => {
    e.preventDefault();
    setIsSubmitting(true);

    const bookingReference = `V2V${Date.now().toString().slice(-8)}`;

    const bookingDetails = {
      ...formData,
      reference: bookingReference,
      totalPrice: 'R450',
      deposit: 'R100',
      bookingDate: new Date().toLocaleDateString('en-ZA')
    };

    // Simulate processing delay
    await new Promise(resolve => setTimeout(resolve, 800));

    generatePDF(bookingDetails); // <-- uses imported generatePDF
    if (onBookingComplete) onBookingComplete(bookingDetails);
    setIsSubmitting(false);
  };

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value
    });
  };

  const isFormValid =
    formData.fullName &&
    formData.cellphone &&
    formData.pickupPoint &&
    formData.dropoffLocation &&
    formData.travelDate;

  return (
    <div className="relative">
      <div className="absolute -top-4 -left-4 w-24 h-24 bg-primary/10 rounded-full blur-2xl" />
      <div className="absolute -bottom-4 -right-4 w-32 h-32 bg-secondary/20 rounded-full blur-2xl" />

      <form
        onSubmit={handleSubmit}
        className="relative bg-card/95 backdrop-blur-sm p-8 md:p-10 rounded-2xl shadow-2xl border border-border/50 overflow-hidden"
      >
        {/* Header */}
        <div className="text-center mb-8">
          <div className="inline-flex items-center justify-center w-16 h-16 bg-gradient-to-br from-primary to-primary/80 rounded-2xl mb-4 shadow-lg">
            <Bus className="w-8 h-8 text-primary-foreground" />
          </div>
          <h3 className="text-2xl font-bold text-foreground mb-2">Book Your Seat</h3>
          <p className="text-muted-foreground">Fill in your details to secure your spot</p>
        </div>

        <div className="space-y-6">
          {/* Full Name */}
          <div className="group">
            <label htmlFor="fullName" className="flex items-center gap-2 text-sm font-semibold text-foreground mb-2">
              <User className="w-4 h-4 text-primary" />
              Full Names <span className="text-destructive">*</span>
            </label>
            <input
              type="text"
              id="fullName"
              name="fullName"
              required
              value={formData.fullName}
              onChange={handleChange}
              className="w-full px-4 py-3.5 bg-background border-2 border-border rounded-xl focus:ring-4 focus:ring-primary/20 focus:border-primary transition-all duration-200 text-foreground placeholder:text-muted-foreground"
              placeholder="Enter your full names"
            />
          </div>

          {/* Cellphone */}
          <div className="group">
            <label htmlFor="cellphone" className="flex items-center gap-2 text-sm font-semibold text-foreground mb-2">
              <Phone className="w-4 h-4 text-primary" />
              Cellphone Number <span className="text-destructive">*</span>
            </label>
            <input
              type="tel"
              id="cellphone"
              name="cellphone"
              required
              value={formData.cellphone}
              onChange={handleChange}
              className="w-full px-4 py-3.5 bg-background border-2 border-border rounded-xl focus:ring-4 focus:ring-primary/20 focus:border-primary transition-all duration-200 text-foreground placeholder:text-muted-foreground"
              placeholder="e.g., 072 979 2961"
            />
          </div>

          {/* Pickup Point */}
          <div className="group">
            <label htmlFor="pickupPoint" className="flex items-center gap-2 text-sm font-semibold text-foreground mb-2">
              <MapPin className="w-4 h-4 text-primary" />
              Pickup Point <span className="text-destructive">*</span>
            </label>
            <select
              id="pickupPoint"
              name="pickupPoint"
              required
              value={formData.pickupPoint}
              onChange={handleChange}
              className="w-full px-4 py-3.5 bg-background border-2 border-border rounded-xl focus:ring-4 focus:ring-primary/20 focus:border-primary transition-all duration-200 text-foreground appearance-none cursor-pointer"
            >
              <option value="" className="text-muted-foreground">Select pickup point</option>
              {pickupPoints.map((point) => (
                <option key={point} value={point}>{point}</option>
              ))}
            </select>
          </div>

          {/* Drop-off Location */}
          <div className="group">
            <label className="flex items-center gap-2 text-sm font-semibold text-foreground mb-3">
              <MapPin className="w-4 h-4 text-secondary" />
              Drop-off Location <span className="text-destructive">*</span>
            </label>
            <div className="space-y-3">
              {['Vaal University of Technology', 'North-West University (Vanderbijlpark)'].map(location => (
                <label 
                  key={location}
                  className={`flex items-center p-4 border-2 rounded-xl cursor-pointer transition-all duration-200 ${
                    formData.dropoffLocation === location
                      ? 'border-primary bg-primary/5 shadow-md'
                      : 'border-border hover:border-primary/50 hover:bg-muted/50'
                  }`}
                >
                  <input
                    type="radio"
                    name="dropoffLocation"
                    value={location}
                    checked={formData.dropoffLocation === location}
                    onChange={handleChange}
                    required
                    className="w-5 h-5 text-primary focus:ring-primary/50 border-2 border-muted-foreground"
                  />
                  <div className="ml-3">
                    <span className="font-medium text-foreground">{location}</span>
                  </div>
                </label>
              ))}
            </div>
          </div>

          {/* Travel Date */}
          <div className="group">
            <label htmlFor="travelDate" className="flex items-center gap-2 text-sm font-semibold text-foreground mb-2">
              <Calendar className="w-4 h-4 text-primary" />
              Travel Date <span className="text-destructive">*</span>
            </label>
            <select
              id="travelDate"
              name="travelDate"
              required
              value={formData.travelDate}
              onChange={handleChange}
              className="w-full px-4 py-3.5 bg-background border-2 border-border rounded-xl focus:ring-4 focus:ring-primary/20 focus:border-primary transition-all duration-200 text-foreground appearance-none cursor-pointer"
            >
              <option value="" className="text-muted-foreground">Select travel date</option>
              {travelDates.map(date => (
                <option key={date} value={date}>{date}</option>
              ))}
            </select>
          </div>

          {/* Pricing Card */}
          <div className="bg-gradient-to-br from-primary/10 via-primary/5 to-secondary/10 border-2 border-primary/20 p-6 rounded-2xl">
            <div className="flex items-center gap-2 mb-4">
              <Sparkles className="w-5 h-5 text-secondary" />
              <span className="font-semibold text-foreground">Price Summary</span>
            </div>
            <div className="space-y-3">
              <div className="flex justify-between items-center">
                <span className="text-muted-foreground">Total Price:</span>
                <span className="text-3xl font-bold text-foreground">R450</span>
              </div>
              <div className="h-px bg-border" />
              <div className="flex justify-between items-center">
                <span className="text-muted-foreground">Required Deposit:</span>
                <span className="text-xl font-bold text-primary">R100</span>
              </div>
            </div>
          </div>

          {/* Submit Button */}
          <button
            type="submit"
            disabled={!isFormValid || isSubmitting}
            className={`w-full px-6 py-4 rounded-xl font-bold text-lg transition-all duration-300 flex items-center justify-center gap-3 ${
              isFormValid && !isSubmitting
                ? 'bg-gradient-to-r from-primary to-primary/90 text-primary-foreground hover:shadow-xl hover:shadow-primary/30 hover:-translate-y-0.5 active:translate-y-0'
                : 'bg-muted text-muted-foreground cursor-not-allowed'
            }`}
          >
            {isSubmitting ? (
              <>
                <div className="w-5 h-5 border-2 border-primary-foreground/30 border-t-primary-foreground rounded-full animate-spin" />
                <span>Processing...</span>
              </>
            ) : (
              <>
                <Download className="w-5 h-5" />
                <span>Submit & Download Receipt</span>
              </>
            )}
          </button>

          <p className="text-center text-sm text-muted-foreground">
            By submitting, you agree to our booking terms and conditions
          </p>
        </div>
      </form>
    </div>
  );
};

export default BookingForm;
