import React, { useState, useEffect } from "react";

const ResidentPinEntryPage = () => {
  const [pin, setPin] = useState(Array(7).fill(""));

  const handlePinChange = (index, value) => {
    const newPin = [...pin];
    newPin[index] = value.slice(0, 1); // Allow only one character
    setPin(newPin);

    if (value && index < 6) {
      document.getElementById(`pin-${index + 1}`).focus(); // Move to next input
    }
  };

  useEffect(() => {
    if (pin.every((digit) => digit !== "")) {
      handleSubmit(); // Auto-submit when all fields are filled
    }
  }, [pin]);

  const handleSubmit = () => {
    console.log("PIN entered:", pin.join(""));
    // Add actual submission logic here
  };

  return (
    <div
      className="d-flex justify-content-center align-items-center"
      style={{ height: "100vh", backgroundColor: "#f8f9fa" }}
    >
      <div
        className="d-flex justify-content-center align-items-center"
        style={{
          width: "90vw",
          height: "40vh",
          display: "grid",
          gridTemplateColumns: "repeat(7, 1fr)",
          gap: "1rem",
        }}
      >
        {pin.map((digit, index) => (
          <input
            key={index}
            id={`pin-${index}`}
            type="text"
            value={digit}
            onChange={(e) => handlePinChange(index, e.target.value)}
            className="form-control"
            style={{
              width: "100%", // Makes each input square fill its column
              height: "100%",
              fontSize: "5rem",
              textAlign: "center",
              border: "3px solid #000",
              borderRadius: "10px",
              backgroundColor: "#fff",
            }}
            maxLength="1"
          />
        ))}
      </div>
    </div>
  );
};

export default ResidentPinEntryPage;
