import React, { useState, useRef, useEffect } from "react";
import { useLocation } from "react-router-dom";

const EnterDetails = () => {
  const location = useLocation();
  const { parcelInfo } = location.state || {};
  const [name, setName] = useState("");
  const [block, setBlock] = useState("");
  const [floor, setFloor] = useState("");
  const [unitNumber, setUnitNumber] = useState("");
  const [phoneNumber, setPhoneNumber] = useState("");
  const [barcode, setBarcode] = useState("");
  const [additionalPhotos, setAdditionalPhotos] = useState([]);
  const [imgurLinks, setImgurLinks] = useState([]);
  const [isCameraOpen, setIsCameraOpen] = useState(false);
  const videoRef = useRef(null);
  const canvasRef = useRef(null);

  const hardcodedPhones = ["+601133255652", "+60198765432", "+60111223344"];

  useEffect(() => {
    if (parcelInfo) {
      setName(parcelInfo.name || "");
      setBlock(parcelInfo.block || "");
      setFloor(parcelInfo.floor || "");
      setUnitNumber(parcelInfo.unitNumber || "");
      setPhoneNumber(parcelInfo.phoneNumber || "");
      setBarcode(parcelInfo.barcode || "");
    }
  }, [parcelInfo]);

  const openCamera = async () => {
    try {
      setIsCameraOpen(true);
      const stream = await navigator.mediaDevices.getUserMedia({ video: true });
      if (videoRef.current) {
        videoRef.current.srcObject = stream;
        videoRef.current.play();
      }
    } catch (error) {
      console.error("Error accessing camera:", error);
    }
  };

  const capturePhoto = () => {
    if (videoRef.current && canvasRef.current) {
      const context = canvasRef.current.getContext("2d");
      canvasRef.current.width = videoRef.current.videoWidth;
      canvasRef.current.height = videoRef.current.videoHeight;
      context.drawImage(
        videoRef.current,
        0,
        0,
        canvasRef.current.width,
        canvasRef.current.height
      );

      const imageData = canvasRef.current.toDataURL("image/png");
      setAdditionalPhotos([...additionalPhotos, imageData]);

      uploadToImgur(imageData);

      videoRef.current.srcObject.getTracks().forEach((track) => track.stop());
      setIsCameraOpen(false);
    }
  };

  const uploadToImgur = async (base64Image) => {
    const IMGUR_CLIENT_ID = "716ded2b5286415"; // Replace with your actual Imgur client ID

    try {
      const response = await fetch("https://api.imgur.com/3/image", {
        method: "POST",
        headers: {
          Authorization: `Client-ID ${IMGUR_CLIENT_ID}`,
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          image: base64Image.split(",")[1],
          type: "base64",
        }),
      });

      const data = await response.json();
      if (data.success) {
        setImgurLinks([...imgurLinks, data.data.link]);
        console.log("Imgur Link Uploaded: ", data.data.link);
      } else {
        console.error("Imgur upload failed:", data);
      }
    } catch (error) {
      console.error("Error uploading to Imgur:", error);
    }
  };

  const sendWhatsAppMessage = () => {
    const imgurText =
      imgurLinks.length > 0
        ? `📷 *Photos:* ${imgurLinks.join(", ")}`
        : "No photos attached";
    const message =
      `Hi there! 👋\n\n` +
      `This is a friendly update from *Boxly* 📦. We've successfully delivered your parcel!\n\n` +
      `👤 *Name:* ${name}\n` +
      `🔢 *Tracking Number:* ${barcode || "Not Available"}\n` +
      `🏠 *Address:*\n - Block: ${block}\n - Floor: ${floor}\n - Unit: ${unitNumber}\n\n` +
      imgurText;

    const encodedMessage = encodeURIComponent(message);
    const whatsappURL = `https://api.whatsapp.com/send?phone=${phoneNumber}&text=${encodedMessage}`;

    window.open(whatsappURL, "_blank");
  };

  return (
    <div className="container mt-5">
      <h2>Enter Delivery Details</h2>
      <p>
        <strong>Tracking Number:</strong> {barcode || "Not Available"}
      </p>

      <div className="mb-3">
        <label>Name:</label>
        <input
          type="text"
          className="form-control"
          value={name}
          onChange={(e) => setName(e.target.value)}
        />
      </div>

      <div className="mb-3">
        <label>Block:</label>
        <input
          type="text"
          className="form-control"
          value={block}
          onChange={(e) => setBlock(e.target.value)}
        />
      </div>

      <div className="mb-3">
        <label>Floor:</label>
        <input
          type="text"
          className="form-control"
          value={floor}
          onChange={(e) => setFloor(e.target.value)}
        />
      </div>

      <div className="mb-3">
        <label>Unit Number:</label>
        <input
          type="text"
          className="form-control"
          value={unitNumber}
          onChange={(e) => setUnitNumber(e.target.value)}
        />
      </div>

      <div className="mb-3">
        <label>Phone Number:</label>
        <select
          className="form-control"
          value={phoneNumber}
          onChange={(e) => setPhoneNumber(e.target.value)}
        >
          <option value="">Select Phone</option>
          {hardcodedPhones.map((num, index) => (
            <option key={index} value={num}>
              {num}
            </option>
          ))}
        </select>
      </div>

      <div className="mb-3">
        <button className="btn btn-secondary" onClick={openCamera}>
          Add More Photos
        </button>
      </div>

      {isCameraOpen && (
        <div className="mb-3">
          <video
            ref={videoRef}
            style={{ width: "100%", maxWidth: "500px" }}
            autoPlay
            playsInline
          />
          <button className="btn btn-primary mt-2" onClick={capturePhoto}>
            Capture Photo
          </button>
        </div>
      )}

      <canvas ref={canvasRef} style={{ display: "none" }} />

      {additionalPhotos.length > 0 && (
        <div className="mb-3">
          <h4>Additional Photos:</h4>
          <div className="d-flex flex-wrap">
            {additionalPhotos.map((photo, index) => (
              <img
                key={index}
                src={photo}
                alt={`Additional ${index + 1}`}
                style={{
                  width: "100px",
                  height: "100px",
                  objectFit: "cover",
                  margin: "5px",
                }}
              />
            ))}
          </div>
        </div>
      )}

      <button
        className="btn btn-success"
        onClick={sendWhatsAppMessage}
        disabled={!unitNumber || !phoneNumber}
      >
        Send WhatsApp Message
      </button>
    </div>
  );
};

export default EnterDetails;
