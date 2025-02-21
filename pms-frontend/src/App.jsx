import "./App.css";
import ListParcelComponent from "./components/ListParcelComponent";
import FooterComponent from "./components/FooterComponent";
import ParcelComponent from "./components/ParcelComponent";
import LoginPage from "./components/LoginPage";
import DeliveryManPage from "./components/DeliveryManPage";
import ResidentPinEntryPage from "./components/ResidentPinEntryPage";
import SignUpPage from "./components/SignUpPage";
import ManagementOfficePage from "./components/ManagementOfficePage";
import EnterDetailsPage from "./components/EnterDetailsPage";
import { BrowserRouter, Route, Routes } from "react-router-dom";

function App() {
  return (
    <>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<LoginPage />} />
          <Route path="/parcels" element={<ListParcelComponent />} />
          <Route path="/add-parcel" element={<ParcelComponent />} />
          <Route path="/parcels/:id" element={<ParcelComponent />} />
          <Route path="/delivery-man" element={<DeliveryManPage />} />
          <Route path="/sign-up" element={<SignUpPage />} />
          <Route path="/login" element={<LoginPage />} />
          <Route path="/enter-details" element={<EnterDetailsPage />} />
          <Route
            path="/resident-pin-entry"
            element={<ResidentPinEntryPage />}
          />
          <Route path="/management-office" element={<ManagementOfficePage />} />
          <Route path="/management-office" element={<ManagementOfficePage />} />
        </Routes>
        <FooterComponent />
      </BrowserRouter>
    </>
  );
}

export default App;
