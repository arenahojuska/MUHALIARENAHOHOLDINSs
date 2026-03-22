import * as React from "react";
import { forwardRef } from "react";

const variantClasses = {
  default: "btn-default",
  destructive: "btn-destructive",
  outline: "btn-outline",
  secondary: "btn-secondary",
  ghost: "btn-ghost",
  link: "btn-link",
};

const sizeClasses = {
  default: "btn-default-size",
  sm: "btn-sm",
  lg: "btn-lg",
  icon: "btn-icon",
};

export const Button = forwardRef(
  ({ className = "", variant = "default", size = "default", asChild = false, ...props }, ref) => {
    const Comp = asChild ? "span" : "button"; // simple replacement for Slot
    const combinedClassName = `${variantClasses[variant]} ${sizeClasses[size]} ${className}`;

    return <Comp ref={ref} className={combinedClassName} {...props} />;
  }
);

Button.displayName = "Button";
