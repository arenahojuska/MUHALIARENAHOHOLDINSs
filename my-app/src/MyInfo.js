import React, { Component } from "react";

class MyInfo extends Component {
  render() {
    const firstname = "Arenaho";
    const lastname = "Juska";
    const hours = new Date().getHours();
    let timeOfDay;
    const styles = {
      fontSize: 30
    };

    if (hours < 12) {
      timeOfDay = "Ndi macheloeni";
      styles.color = "#1E90FF";
    } else if (hours >= 12 && hours < 18) {
      timeOfDay = "Ndi Masiani";
      styles.color = "#65f1ef";
    } else {
      timeOfDay = "Edelani!";
      styles.color = "#ff9600";
    }

    return (
      <div>
        <h1 style={styles}>
          {timeOfDay}, MUHALI {firstname + " " + lastname}
        </h1>
      </div>
    );
  }
}

export default MyInfo;
