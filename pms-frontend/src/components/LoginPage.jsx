import { useNavigate } from "react-router-dom";

const LoginPage = () => {
  const navigate = useNavigate();

  return (
    <div
      className="container-fluid d-flex flex-column justify-content-center align-items-center"
      style={{
        height: "90vh",
        padding: 0,
        overflow: "hidden",
        position: "relative",
      }}
    >
      {/* Top-right buttons */}
      <div
        className="position-fixed top-0 end-0 mt-3 me-3 d-flex gap-3"
        style={{ zIndex: 1000 }}
      >
        <button
          className="btn btn-link text-dark fw-bold"
          style={{ fontSize: "1rem" }}
          onClick={() => navigate("/sign-up")}
        >
          User Sign Up
        </button>
        <button
          className="btn btn-link text-dark"
          onClick={() => navigate("/management-office")}
          style={{ fontSize: "1rem" }}
        >
          <i
            className="fa-solid fa-people-roof"
            style={{ fontSize: "1.5rem" }}
          ></i>
        </button>
      </div>

      {/* Main Selection Buttons (Side by Side & Centered) */}
      <div
        className="d-flex justify-content-center align-items-center gap-5"
        style={{ minHeight: "50vh", width: "100%" }}
      >
        <button
          className="d-flex flex-column justify-content-center align-items-center"
          onClick={() => navigate("/delivery-man")}
          style={{
            fontSize: "1.2rem",
            padding: "20px",
            width: "400px",
            height: "400px",
            borderRadius: "20px",
            border: "3px solid #007bff",
            backgroundColor: "transparent",
            color: "#007bff",
            transition: "all 0.3s ease-in-out",
            fontWeight: "bold",
          }}
          onMouseEnter={(e) => {
            e.target.style.transform = "scale(1.05)";
            e.target.style.boxShadow = "4px 4px 10px rgba(0, 123, 255, 0.3)";
          }}
          onMouseLeave={(e) => {
            e.target.style.transform = "scale(1)";
            e.target.style.boxShadow = "none";
          }}
        >
          <i
            className="fa-solid fa-truck-fast"
            style={{ fontSize: "5rem" }}
          ></i>
          <span>Delivery Man</span>
        </button>

        <button
          className="d-flex flex-column justify-content-center align-items-center"
          onClick={() => navigate("/resident-pin-entry")}
          style={{
            fontSize: "1.2rem",
            padding: "20px",
            width: "400px",
            height: "400px",
            borderRadius: "20px",
            border: "3px solid #007bff",
            backgroundColor: "transparent",
            color: "#007bff",
            transition: "all 0.3s ease-in-out",
            fontWeight: "bold",
          }}
          onMouseEnter={(e) => {
            e.target.style.transform = "scale(1.05)";
            e.target.style.boxShadow = "4px 4px 10px rgba(0, 123, 255, 0.3)";
          }}
          onMouseLeave={(e) => {
            e.target.style.transform = "scale(1)";
            e.target.style.boxShadow = "none";
          }}
        >
          <i className="fa-solid fa-house" style={{ fontSize: "5rem" }}></i>
          <span>Resident</span>
        </button>
      </div>
    </div>
  );
};

export default LoginPage;
