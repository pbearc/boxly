import React, { useState, useRef } from "react";

const SignUpPage = () => {
  const [block, setBlock] = useState("");
  const [floor, setFloor] = useState("");
  const [unit, setUnit] = useState("");
  const [pin, setPin] = useState(["", "", "", "", "", "", ""]);
  const [phoneNumbers, setPhoneNumbers] = useState([""]);

  // Refs for each pin input field
  const pinRefs = useRef([]);

  // Handle pin change and focus management
  const handlePinChange = (index, value) => {
    const updatedPin = [...pin];
    updatedPin[index] = value;
    setPin(updatedPin);

    // Focus on the next input if a digit is entered
    if (value !== "" && index < pin.length - 1) {
      pinRefs.current[index + 1].focus();
    }
  };

  // Handle pin delete and focus management
  const handlePinDelete = (index) => {
    const updatedPin = [...pin];
    updatedPin[index] = "";
    setPin(updatedPin);

    // Focus on the previous input if a digit is deleted
    if (index > 0) {
      pinRefs.current[index - 1].focus();
    }
  };

  const handlePhoneChange = (index, value) => {
    const updatedPhoneNumbers = [...phoneNumbers];
    updatedPhoneNumbers[index] = value;
    setPhoneNumbers(updatedPhoneNumbers);
  };

  const addPhoneField = () => {
    setPhoneNumbers([...phoneNumbers, ""]);
  };

  const removePhoneField = (index) => {
    const updatedPhoneNumbers = phoneNumbers.filter((_, i) => i !== index);
    setPhoneNumbers(updatedPhoneNumbers);
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    // Handle the form submission logic here
    console.log({
      block,
      floor,
      unit,
      pin: pin.join(""), // Join pin digits to send as a single string
      phoneNumbers,
    });
  };

  return (
    <div className="container mt-5">
      <h2>Sign Up for Parcel Collection</h2>
      <form onSubmit={handleSubmit}>
        {/* Block, Floor, Unit - Side by Side */}
        <div className="row mb-3">
          <div className="col">
            <label htmlFor="block" className="form-label">
              Block
            </label>
            <input
              type="text"
              className="form-control"
              id="block"
              value={block}
              onChange={(e) => setBlock(e.target.value)}
              required
            />
          </div>
          <div className="col">
            <label htmlFor="floor" className="form-label">
              Floor
            </label>
            <input
              type="text"
              className="form-control"
              id="floor"
              value={floor}
              onChange={(e) => setFloor(e.target.value)}
              required
            />
          </div>
          <div className="col">
            <label htmlFor="unit" className="form-label">
              Unit
            </label>
            <input
              type="text"
              className="form-control"
              id="unit"
              value={unit}
              onChange={(e) => setUnit(e.target.value)}
              required
            />
          </div>
        </div>

        {/* 7-Digit Pin (Square Input Boxes) */}
        <div className="mb-3">
          <label htmlFor="pin" className="form-label">
            7-Digit Pin
          </label>
          <div className="d-flex">
            {pin.map((digit, index) => (
              <input
                key={index}
                ref={(el) => (pinRefs.current[index] = el)} // Assign ref to each pin input
                type="text"
                className="form-control text-center mx-1"
                value={digit}
                onChange={(e) => handlePinChange(index, e.target.value)}
                onKeyDown={(e) => {
                  if (e.key === "Backspace" && digit === "") {
                    handlePinDelete(index); // Focus on previous box when backspace is pressed on empty field
                  }
                }}
                maxLength="1"
                style={{ width: "50px", height: "50px", fontSize: "20px" }}
              />
            ))}
          </div>
          <small className="form-text text-muted">
            This pin is used for retrieving your parcel in the future. We want
            to keep your parcel information private, so you'll need to type in
            this pin every time you want to collect your parcel.
          </small>
        </div>

        {/* Phone Numbers */}
        <div className="mb-3">
          <label htmlFor="phone" className="form-label">
            Household Phone Numbers
          </label>
          {phoneNumbers.map((phone, index) => (
            <div key={index} className="input-group mb-2">
              <input
                type="tel"
                className="form-control"
                placeholder="Phone Number"
                value={phone}
                onChange={(e) => handlePhoneChange(index, e.target.value)}
                required
              />
              <button
                type="button"
                className="btn btn-danger"
                onClick={() => removePhoneField(index)}
              >
                &times;
              </button>
            </div>
          ))}
          <button
            type="button"
            className="btn btn-primary"
            onClick={addPhoneField}
            style={{
              borderRadius: "50%",
              width: "40px",
              height: "40px",
              padding: "0",
            }}
          >
            <span className="h4">+</span>
          </button>
        </div>

        <button type="submit" className="btn btn-success">
          Sign Up
        </button>
      </form>
    </div>
  );
};

export default SignUpPage;
