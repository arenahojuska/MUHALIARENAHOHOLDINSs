import { Play, Star, TrendingUp, Sparkles, Award, Users } from "lucide-react";
import heroImage from "../assets/hero-image.jpg";
import { Button } from "./button";
import TextType from "./TextType";
export const Hero = () => {
  return (
    <section className="relative overflow-hidden bg-gradient-to-br from-background via-background to-primary/5">
      {/* Abstract gradient shapes */}
      <div className="absolute top-20 right-10 w-96 h-96 bg-gradient-accent rounded-full blur-3xl opacity-60 animate-pulse" />
      <div className="absolute bottom-20 left-10 w-[600px] h-[600px] bg-gradient-accent rounded-full blur-3xl opacity-40 animate-pulse" />

      <div className="container mx-auto px-4 sm:px-6 lg:px-8 relative z-10">
        <div className="grid lg:grid-cols-2 gap-16 items-center min-h-[calc(100vh-8rem)] py-16">
          {/* Left Content */}
          <div className="space-y-8">
            <div className="space-y-6">
              <div className="inline-block">
                <span className="bg-gradient-primary text-primary-foreground text-xs sm:text-sm font-bold px-6 py-3 rounded-full shadow-[var(--shadow-glow)] uppercase tracking-wider inline-flex items-center gap-2">
                  <Sparkles className="h-4 w-4" />
                  Muhali Design IT Solutions
                </span>
              </div>
<h1 className="text-4xl sm:text-5xl lg:text-7xl font-bold leading-[1.2] text-foreground">
  Transform Your Vision into{" "}
  <span className="whitespace-nowrap min-w-fit relative">
    <TextType
      text={[
        "Digital Excellence",
        "Modern Solutions",
        "Seamless Experiences",
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


        <p className="text-lg sm:text-xl max-w-2xl leading-relaxed text-gray-600 dark:text-gray-300 mt-6 tracking-wide">
  <span className="font-semibold text-gray-800 dark:text-white">
    Grow Your Business
  </span>{" "}
  with{" "}
  <span className="text-blue-600 dark:text-blue-400 font-semibold">
    Affordable Web Design
  </span>{" "}
  and{" "}
  <span className="text-purple-600 dark:text-purple-400 font-semibold">
    Digital Marketing
  </span>
  . We craft{" "}
  <span className="italic text-pink-600 dark:text-pink-400">
    stunning websites
  </span>{" "}
  and{" "}
  <span className="italic text-indigo-600 dark:text-indigo-400">
    powerful digital strategies
  </span>{" "}
  that drive real results. <br className="hidden sm:block" />
  <span className="text-gray-700 dark:text-gray-200">
    Partner with experts who turn your business goals into{" "}
    <span className="font-semibold text-green-600 dark:text-green-400">
      measurable success.
    </span>
  </span>
</p>

            </div>

            <div className="flex flex-col sm:flex-row gap-4 items-start sm:items-center">
  <Button
  size="lg"
  className="text-base sm:text-lg md:text-xl px-10 py-6 uppercase font-bold tracking-wider rounded-xl shadow-lg hover:shadow-2xl hover:scale-105 transition-transform duration-300 bg-gradient-to-r from-blue-500 via-purple-500 to-pink-500 text-white"
>
  Start Your Project
</Button>


              <Button variant="play" size="lg" className="gap-3 group">
                <div className="w-12 h-12 rounded-full bg-gradient-primary flex items-center justify-center group-hover:scale-110 transition-transform">
                  <Play className="h-5 w-5 text-primary-foreground fill-primary-foreground ml-0.5" />
                </div>
                <span className="font-semibold text-base">See Our Work</span>
              </Button>
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
  <img 
    src={heroImage} 
    alt="Professional digital marketing team delivering innovative web design solutions and business growth strategies" 
    className="w-full h-auto object-cover transition-transform duration-700 ease-in-out transform hover:scale-105"
  />

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
          <div className="absolute -top-6 -right-6 bg-card/95 backdrop-blur-md border border-border/50 rounded-3xl shadow-2xl p-6 animate-float hidden lg:block hover:scale-105 transition-transform duration-300">
  <div className="flex items-center gap-3 mb-3">
    <div className="flex space-x-0.5">
      {[...Array(5)].map((_, i) => (
        <Star
          key={i}
          className="h-5 w-5 text-yellow-400 fill-yellow-400 drop-shadow-md"
        />
      ))}
    </div>
  </div>
  <p className="text-2xl font-bold bg-gradient-to-r from-blue-500 via-purple-500 to-pink-500 bg-clip-text text-transparent">
    5.0 Rating
  </p>
  <p className="text-xs text-muted-foreground mt-1.5">
    From 850+ Happy Clients
  </p>
</div>

            {/* Success Rate Badge - Left Side */}
            <div className="absolute top-1/3 -left-8 bg-card/95 backdrop-blur-sm border border-border/50 rounded-2xl shadow-2xl p-5 animate-float-delayed hidden lg:block">
              <div className="flex items-center gap-3">
                <div className="w-14 h-14 rounded-xl bg-gradient-secondary flex items-center justify-center">
                  <Award className="h-7 w-7 text-secondary-foreground" />
                </div>
                <div>
                  <p className="text-xs text-muted-foreground font-medium">Success Rate</p>
                  <div className="flex items-center gap-2 mt-1">
                    <div className="text-3xl font-bold text-foreground">99%</div>
                    <div className="w-2.5 h-2.5 rounded-full bg-primary animate-pulse" />
                  </div>
                </div>
              </div>
            </div>

         {/* Growth Chart */}
<div className="absolute -bottom-6 -left-6 bg-card/95 backdrop-blur-sm border border-border/50 rounded-3xl shadow-2xl p-6 animate-float hidden lg:block">
  <div className="flex items-center gap-2 mb-4">
    <TrendingUp className="h-5 w-5 text-primary" />
    <span className="font-bold text-foreground text-sm">Business Growth</span>
  </div>

  <div className="flex items-end gap-3 h-28">
    {[50, 70, 60, 85, 75, 100].map((height, i) => (
      <div
        key={i}
        className="w-8 rounded-t-xl bg-gradient-to-t from-blue-500 via-blue-400 to-purple-400 transition-all duration-500 ease-out transform hover:scale-y-105 cursor-pointer relative group"
        style={{ height: `${height}%` }}
      >
        {/* Tooltip */}
        <div className="absolute -top-8 left-1/2 -translate-x-1/2 opacity-0 group-hover:opacity-100 transition-opacity bg-foreground text-background text-xs px-2 py-1 rounded shadow-md whitespace-nowrap">
          {height}%
        </div>

        {/* Glow on hover */}
        <div className="absolute inset-x-0 bottom-0 h-1 rounded-t-xl bg-white/30 opacity-0 group-hover:opacity-50 transition-opacity"></div>
      </div>
    ))}
  </div>

  <p className="text-xs text-primary font-semibold mt-4 flex items-center gap-1">
    <TrendingUp className="h-3 w-3" />
    +240% Average Growth
  </p>


            </div>
          </div>
        </div>
      </div>
    </section>
  );
};
