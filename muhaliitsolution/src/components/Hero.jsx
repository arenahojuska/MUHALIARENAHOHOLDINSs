import { Play, Star, TrendingUp, Sparkles, Award, Users } from "lucide-react";
import { Link } from "react-router-dom"; 

import { Button } from "./button";
import TextType from "./TextType";
import FloatingLines from "./hero-background";

export const Hero = () => {
  return (
    <section className="relative overflow-hidden bg-gradient-to-br from-background via-background to-primary/5">
      {/* Abstract gradient shapes */}
      <div className="absolute inset-0 -z-10">
  <FloatingLines 
    enabledWaves={['top', 'middle', 'bottom']}                                                                                 
   
    lineCount={[10, 15, 20]}
   
    lineDistance={[8, 6, 4]}
    bendRadius={5.0}
    bendStrength={-0.5}
    interactive={true}
    parallax={true}
  />
</div>
      <div className="absolute top-20 right-10 w-96 h-96 bg-gradient-accent rounded-full blur-3xl opacity-60 animate-pulse" />
      <div className="absolute bottom-20 left-10 w-[600px] h-[600px] bg-gradient-accent rounded-full blur-3xl opacity-40 animate-pulse" />

      <div className="container mx-auto px-4 sm:px-6 lg:px-8 relative z-10">
        <div className="grid lg:grid-cols-2 gap-16 items-center min-h-[calc(100vh-8rem)] py-16">
          {/* Left Content */}
          <div className="space-y-8">
            <div className="space-y-6">
              <div className="inline-block">
                <span className="bg-gradient-to-r from-primary to-accent text-white text-lg sm:text-xl font-extrabold px-8 py-4 rounded-full shadow-[0_0_15px_rgba(255,255,255,0.3),0_0_30px_rgba(255,255,255,0.2)] uppercase tracking-wider inline-flex items-center gap-3 hover:scale-105 transition-transform duration-300">
  <Sparkles className="h-5 w-5" />
  Muhali Software Solutions
</span>

              </div>
<h1 className="text-4xl sm:text-5xl lg:text-7xl font-bold leading-[1.2] text-foreground">
  Transform Your Vision into{" "}
  <span className="whitespace-nowrap min-w-fit relative">
    <TextType
      text={[
        "Stunning websites that captivate audiences",
        "Digital experiences that drive real results",
        "Custom web solutions that elevate your brand.",
      ]}
      typingSpeed={45}
      pauseDuration={1500}
      showCursor={true}
      cursorCharacter="|"
      renderText={(text) => (
        <span
          className="bg-gradient-to-r from-blue-500 via-purple-500 to-pink-500 bg-clip-text text-transparent"
          style={{
            WebkitBackgroundClip: "text",
            WebkitTextFillColor: "transparent",
          }}
        >
          {text}
        </span>
      )}
    />
  </span>
</h1>
<h1> </h1>
<h1> </h1>

<p className="text-lg sm:text-xl max-w-2xl leading-relaxed mt-6 tracking-wide text-white font-medium">
  
  <span className="font-bold text-blue-400">
    Grow Your Business With Affordable Web Design
  </span>{" "}
  and{" "}
  <span className="font-bold text-purple-400">
    Digital Marketing {" "}      
  </span>
  
  <span className="italic font-semibold text-pink-400">
    We Develop   stunning websites
  </span>{" "}
  and{" "}
  <span className="italic font-semibold text-indigo-400">
    powerful digital strategies
  </span>{" "}
 {" "} 
<span className="text-yellow-800 font-semibold">
   that drive real results.
</span>
<br className="sm:block" />

{" "}
<span className="font-extrabold text-green-800">
  Partner with experts who turn your business goals into measurable success.
</span>

</p>



            </div>

            <div className="flex flex-col sm:flex-row gap-4 items-start sm:items-center">
<Link to="/contact">
  <Button
    size="lg"
    className="text-base sm:text-lg md:text-xl px-10 py-6 uppercase font-bold tracking-wider rounded-xl shadow-lg hover:shadow-2xl hover:scale-105 transition-transform duration-300 bg-gradient-to-r from-blue-500 via-purple-500 to-pink-500 text-white"
  >
    Start Your Project
  </Button>
</Link>


              
            </div>

            {/* Trust indicators */}
            <div className="flex items-center gap-8 pt-6 flex-wrap">
              <div className="group cursor-pointer text-center">
                <div className="text-4xl font-bold bg-gradient-to-r from-blue-500 via-purple-500 to-pink-500 bg-clip-text text-transparent">
                  850+
                </div>
                <div className="text-sm text-muted-foreground font-medium flex items-center justify-center gap-1.5 mt-1">
                  <Users className="h-4 w-4" />
                  Projects Delivered
                </div>
              </div>

              <div className="h-12 w-px bg-border" />

              <div className="group cursor-pointer text-center">
                <div className="text-4xl font-bold bg-gradient-to-r from-green-400 via-yellow-400 to-red-400 bg-clip-text text-transparent">
                  99%
                </div>
                <div className="text-sm text-muted-foreground font-medium flex items-center justify-center gap-1.5 mt-1">
                  <Award className="h-4 w-4" />
                  Client Satisfaction
                </div>
              </div>

              <div className="h-12 w-px bg-border" />

              <div className="group cursor-pointer text-center">
                <div className="flex items-center justify-center gap-1.5">
                  {[...Array(5)].map((_, i) => (
                    <Star key={i} className="h-5 w-5 fill-yellow-400 text-yellow-400" />
                  ))}
                </div>
                <div className="text-sm text-muted-foreground font-medium mt-1.5">5-Star Excellence</div>
              </div>
            </div>
          </div>

          {/* Right Content - Image with Floating Elements */}
          <div className="relative">
            {/* Hero Image */}
     <div className="relative rounded-3xl overflow-hidden shadow-2xl ring-1 ring-border/50">
  {/* Hero Image */}



  {/* Gradient Overlay */}
  <div className="absolute inset-0 bg-gradient-to-t from-black/40 via-transparent to-black/10" />

  {/* Soft Glow */}
  <div className="absolute inset-0 pointer-events-none rounded-3xl shadow-[0_0_60px_rgba(59,130,246,0.3)]" />

  {/* Optional Highlight Badge */}
  <div className="absolute top-6 left-6 bg-gradient-to-r from-blue-500 via-purple-500 to-pink-500 px-4 py-2 rounded-full text-white font-semibold text-sm shadow-lg animate-pulse">
    Featured Project
  </div>

  {/* Optional Floating Star Elements */}
  <div className="absolute top-10 right-10 w-6 h-6 bg-yellow-400 rounded-full animate-bounce opacity-70" />
  <div className="absolute bottom-10 left-12 w-4 h-4 bg-purple-400 rounded-full animate-bounce opacity-60" />
</div>


            {/* Rating Badge - Top Right */}


         

         

 




            
          </div>
        </div>
      </div>
    </section>
  );
};
