export default function Hero() {
  return (
    <section className="min-h-screen bg-gradient-to-br from-[#0B1C2C] via-[#102A43] to-[#1C3D5A] text-white flex items-center">
      <div className="container mx-auto px-6 py-16 grid md:grid-cols-2 gap-10 items-center">
        
        {/* LEFT SIDE */}
        <div>
          <h1 className="text-4xl md:text-6xl font-bold mb-6 leading-tight">
            PM Mahlathi <br />
            <span className="text-[#D4AF37]">Attorneys Inc</span>
          </h1>

          <h2 className="text-2xl md:text-3xl font-semibold mb-4 text-[#D4AF37]">
            Virtual & Physical
          </h2>

          <p className="text-lg mb-6 text-gray-300">
            Legal consultations made easy. We provide professional legal services 
            through in-person and online platforms like Zoom and Microsoft Teams.
          </p>

          <div className="mb-6">
            <p className="font-semibold">📞 0685853645 / 0888160462</p>
            <p className="font-semibold">📧 mahlathipm@gmail.com</p>
          </div>

          <div className="flex gap-4">
            <button className="bg-[#D4AF37] text-black px-6 py-3 rounded-lg font-semibold hover:opacity-90 transition">
              Get Consultation
            </button>
            <button className="border border-[#D4AF37] px-6 py-3 rounded-lg hover:bg-[#D4AF37] hover:text-black transition">
              Our Services
            </button>
          </div>
        </div>

        {/* RIGHT SIDE - SERVICES */}
        <div className="bg-white text-black rounded-xl shadow-xl p-6">
          <h3 className="text-xl font-bold mb-4 text-center border-b pb-2">
            Our Services
          </h3>

          <ul className="space-y-2 text-sm">
            <li>• Civil Litigation</li>
            <li>• Criminal Law Matters</li>
            <li>• Bail Application</li>
            <li>• Commercial Litigation</li>
            <li>• Contract Drafting</li>
            <li>• Labour Law Matters</li>
            <li>• RAF Claims</li>
            <li>• Medical Negligence</li>
            <li>• Divorce</li>
            <li>• Debt Collection</li>
            <li>• Immigration Matters</li>
          </ul>
        </div>

      </div>
    </section>
  );
}