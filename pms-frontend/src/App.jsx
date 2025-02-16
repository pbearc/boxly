import "./App.css";
import ListParcelComponent from "./components/ListParcelComponent";
import HeaderComponent from "./components/HeaderComponent";
import FooterComponent from "./components/FooterComponent";
import ParcelComponent from "./components/ParcelComponent";
import { BrowserRouter, Route, Routes } from "react-router-dom";

function App() {
  return (
    <>
      <BrowserRouter>
        <HeaderComponent />
        <Routes>
          <Route path="/" element={<ListParcelComponent />} />
          <Route path="/parcels" element={<ListParcelComponent />} />
          <Route path="/add-parcel" element={<ParcelComponent />} />
          <Route path="/parcels/:id" element={<ParcelComponent />} />
        </Routes>
        <FooterComponent />
      </BrowserRouter>
    </>
  );
}

export default App;
