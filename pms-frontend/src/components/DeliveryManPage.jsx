import React, { useState, useRef } from "react";
import { useNavigate } from "react-router-dom";
import { parseParcelInfo } from "../services/parcelService";

const DeliveryManPage = () => {
  const videoRef = useRef(null);
  const navigate = useNavigate();
  const [isCapturing, setIsCapturing] = useState(false);

  const startCamera = async () => {
    try {
      const stream = await navigator.mediaDevices.getUserMedia({ video: true });
      if (videoRef.current) videoRef.current.srcObject = stream;
    } catch (error) {
      console.error("Camera access denied:", error);
    }
  };

  const capturePhoto = async () => {
    setIsCapturing(true);
    const video = videoRef.current;
    const canvas = document.createElement("canvas");
    canvas.width = video.videoWidth;
    canvas.height = video.videoHeight;
    canvas.getContext("2d").drawImage(video, 0, 0);

    const blob = await new Promise((resolve) =>
      canvas.toBlob(resolve, "image/jpeg")
    );
    const formData = new FormData();
    formData.append("image", blob, "parcel_info.jpg");

    try {
      const parcelInfo = await parseParcelInfo(formData);
      navigate("/enter-details", { state: { parcelInfo } });
    } catch (error) {
      console.error("Error parsing parcel info:", error);
      setIsCapturing(false);
    }
  };

  return (
    <div className="container mt-5 text-center">
      <h2>Capture Parcel Information</h2>
      <video
        ref={videoRef}
        autoPlay
        className="border rounded"
        style={{ width: "100%", maxWidth: "400px" }}
      />
      <button
        className="btn btn-primary mt-3"
        onClick={startCamera}
        disabled={isCapturing}
      >
        Start Camera
      </button>
      <button
        className="btn btn-success mt-3 ml-2"
        onClick={capturePhoto}
        disabled={isCapturing}
      >
        Capture Photo
      </button>
      {isCapturing && <p>Processing image, please wait...</p>}
    </div>
  );
};

export default DeliveryManPage;
