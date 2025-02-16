import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { getAllParcels, deleteParcel } from "../services/ParcelService"; // ✅ Import the correct API functions

const ListParcelComponent = () => {
  const [parcels, setParcels] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    fetchParcels();
  }, []);

  function fetchParcels() {
    getAllParcels()
      .then((response) => {
        setParcels(response.data);
      })
      .catch((error) => console.log(error));
  }

  const handleDeleteParcel = (id) => {
    deleteParcel(id)
      .then(() => {
        fetchParcels(); // ✅ Refresh the list after deletion
      })
      .catch((error) => console.log(error));
  };

  return (
    <div className="container mt-4">
      <div className="row mb-4">
        <div className="col">
          <h2 className="text-center">List of Parcels</h2>
        </div>
      </div>
      <div className="row mb-3">
        <div className="col text-end">
          <button
            className="btn btn-primary"
            onClick={() => navigate("/add-parcel")}
          >
            <i className="bi bi-plus-circle"></i> Add Parcel
          </button>
        </div>
      </div>
      <div className="row">
        <div className="col">
          <table className="table table-hover table-bordered">
            <thead className="table-dark">
              <tr>
                <th>ID</th>
                <th>Block</th>
                <th>Floor</th>
                <th>Unit Number</th>
                <th>Status</th>
                <th>Actions</th>
              </tr>
            </thead>
            <tbody>
              {parcels.map((parcel) => (
                <tr key={parcel.id}>
                  <td>{parcel.id}</td>
                  <td>{parcel.block}</td>
                  <td>{parcel.floor}</td>
                  <td>{parcel.unitNumber}</td>
                  <td>{parcel.status || "Pending"}</td>
                  <td>
                    <button
                      className="btn btn-info btn-sm me-2"
                      onClick={() => navigate(`/parcels/${parcel.id}`)}
                    >
                      View
                    </button>
                    <button
                      className="btn btn-danger btn-sm"
                      onClick={() => handleDeleteParcel(parcel.id)}
                    >
                      Delete
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
};

export default ListParcelComponent;
