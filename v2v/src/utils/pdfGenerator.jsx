import { jsPDF } from 'jspdf';

export const generatePDF = (details) => {
  const doc = new jsPDF();

  const pageWidth = doc.internal.pageSize.getWidth();
  const margin = 20;
  let yPosition = 20;

  doc.setFontSize(24);
  doc.setFont('helvetica', 'bold');
  doc.text('V2V', pageWidth / 2, yPosition, { align: 'center' });

  yPosition += 10;
  doc.setFontSize(14);
  doc.setFont('helvetica', 'normal');
  doc.text('Student Transport & Accommodation', pageWidth / 2, yPosition, { align: 'center' });

  yPosition += 15;
  doc.setFontSize(18);
  doc.setFont('helvetica', 'bold');
  doc.text('BOOKING RECEIPT', pageWidth / 2, yPosition, { align: 'center' });

  yPosition += 15;
  doc.setLineWidth(0.5);
  doc.line(margin, yPosition, pageWidth - margin, yPosition);

  yPosition += 12;
  doc.setFontSize(11);
  doc.setFont('helvetica', 'normal');
  doc.text(`Booking Date: ${details.bookingDate}`, margin, yPosition);
  yPosition += 7;
  doc.setFontSize(13);
  doc.setFont('helvetica', 'bold');
  doc.text(`Booking Reference: ${details.reference}`, margin, yPosition);

  yPosition += 15;
  doc.setFontSize(14);
  doc.setFont('helvetica', 'bold');
  doc.text('Passenger Details', margin, yPosition);

  yPosition += 10;
  doc.setFontSize(11);
  doc.setFont('helvetica', 'normal');

  const passengerDetails = [
    { label: 'Full Names:', value: details.fullName },
    { label: 'Cellphone Number:', value: details.cellphone },
    { label: 'Pickup Point:', value: details.pickupPoint },
    { label: 'Drop-off Location:', value: details.dropoffLocation },
    { label: 'Travel Date:', value: details.travelDate }
  ];

  passengerDetails.forEach(item => {
    doc.setFont('helvetica', 'bold');
    doc.text(item.label, margin, yPosition);
    doc.setFont('helvetica', 'normal');
    doc.text(item.value, margin + 50, yPosition);
    yPosition += 7;
  });

  yPosition += 8;
  doc.setLineWidth(0.5);
  doc.line(margin, yPosition, pageWidth - margin, yPosition);

  yPosition += 12;
  doc.setFontSize(14);
  doc.setFont('helvetica', 'bold');
  doc.text('Payment Details', margin, yPosition);

  yPosition += 10;
  doc.setFontSize(11);
  doc.setFont('helvetica', 'normal');
  doc.text('Total Price:', margin, yPosition);
  doc.setFont('helvetica', 'bold');
  doc.text(details.totalPrice, margin + 50, yPosition);

  yPosition += 7;
  doc.setFont('helvetica', 'normal');
  doc.text('Required Deposit:', margin, yPosition);
  doc.setFont('helvetica', 'bold');
  doc.text(details.deposit, margin + 50, yPosition);

  yPosition += 15;
  doc.setFillColor(255, 243, 205);
  doc.rect(margin, yPosition, pageWidth - 2 * margin, 45, 'F');

  yPosition += 8;
  doc.setFontSize(12);
  doc.setFont('helvetica', 'bold');
  doc.text('PAYMENT INSTRUCTIONS', margin + 5, yPosition);

  yPosition += 8;
  doc.setFontSize(10);
  doc.setFont('helvetica', 'normal');
  //doc.text(['Please note that booking is non refundable,', 'and if you want to change the travel date,', 'you can only do it 48 hours before the travel date.'], margin + 5, yPosition + 10);
  doc.text('To confirm your booking, please pay a deposit of R100 With your Name and Whatsapp Number as reference to:', margin + 5, yPosition);
  

yPosition += 8;
doc.setFont('helvetica', 'normal');
doc.text(
  'Please note that booking is non refundable,\nand if you want to change the travel date,\nyou can only do it 48 hours before the travel date.',
  margin + 5,
  yPosition
);


yPosition += 15;
doc.text(
  'Send proof of payment via WhatsApp to +27728009120 or +27785692707',
  margin + 5,
  yPosition
);

yPosition += 5;
doc.text('or email arenahojuska@gmail.com', margin + 5, yPosition);

  yPosition += 20;
  doc.setFontSize(9);
  doc.setTextColor(100);
  doc.text('Important: Your booking is only confirmed once payment is received and verified.', pageWidth / 2, yPosition, { align: 'center' });
  yPosition += 5;
  doc.text('Please keep this receipt for your records.', pageWidth / 2, yPosition, { align: 'center' });

  yPosition = doc.internal.pageSize.getHeight() - 20;
  doc.setLineWidth(0.3);
  doc.line(margin, yPosition, pageWidth - margin, yPosition);
  yPosition += 6;
  doc.setFontSize(9);
  doc.setTextColor(0);
  doc.text('V2V - Reliable Student Transport & Accommodation', pageWidth / 2, yPosition, { align: 'center' });
  yPosition += 4;
  doc.text('WhatsApp: +27728009120 | Email: arenahojuska@gmail.com', pageWidth / 2, yPosition, { align: 'center' });

  doc.save(`V2V-Booking-${details.reference}.pdf`);
};
