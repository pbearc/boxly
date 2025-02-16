import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { createParcel, updateParcel } from "../services/ParcelService";
import { toast } from "react-toastify";
import { getParcelById } from "../services/ParcelService";

const ParcelComponent = () => {
  const navigate = useNavigate();

  const [parcel, setParcel] = useState({
    block: "",
    floor: "",
    unitNumber: "",
  });

  const [error, setError] = useState({
    block: "",
    floor: "",
    unitNumber: "",
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setParcel((prevState) => ({
      ...prevState,
      [name]: value,
    }));
  };

  //save or update parcel
  const handleSubmit = (e) => {
    e.preventDefault();
    if (validate()) {
      if (id) {
        updateParcel(id, parcel)
          .then(() => {
            navigate("/parcels");
            toast.success("Parcel created successfully");
          })
          .catch(() => {
            toast.error("Failed to create parcel");
          });
      } else {
        createParcel(parcel)
          .then(() => {
            navigate("/parcels");
            toast.success("Parcel created successfully");
          })
          .catch(() => {
            toast.error("Failed to create parcel");
          });
      }
    }
  };

  const validate = () => {
    let formIsValid = true;
    const errorsCopy = { ...error };

    if (!parcel.block) {
      formIsValid = false;
      errorsCopy.block = "Block is required";
    }

    if (!parcel.floor) {
      formIsValid = false;
      errorsCopy.floor = "Floor is required";
    }

    if (!parcel.unitNumber) {
      formIsValid = false;
      errorsCopy.unitNumber = "Unit number is required";
    }

    setError(errorsCopy);
    return formIsValid;
  };

  const { id } = useParams();

  function pageTitle() {
    if (id) {
      return <h3 className="card-title mb-0">Edit Parcel</h3>;
    } else {
      return <h3 className="card-title mb-0">Add New Parcel</h3>;
    }
  }

  useEffect(() => {
    if (id) {
      getParcelById(id)
        .then((response) => {
          setParcel(response.data);
        })
        .catch((error) => {
          console.log(error);
        });
    }
  }, [id]);

  return (
    <div className="container mt-4">
      <div className="row">
        <div className="col-md-6 offset-md-3">
          <div className="card">
            <div className="card-header bg-primary text-white">
              {pageTitle()}
            </div>
            <div className="card-body">
              <form onSubmit={handleSubmit}>
                <div className="mb-3">
                  <label className="form-label">Block</label>
                  <input
                    type="text"
                    className={`form-control ${
                      error.block ? "is-invalid" : ""
                    }`}
                    name="block"
                    value={parcel.block}
                    onChange={handleChange}
                    placeholder="Enter block number"
                  />
                  {error.block && (
                    <div className="invalid-feedback">{error.block}</div>
                  )}
                </div>
                <div className="mb-3">
                  <label className="form-label">Floor</label>
                  <input
                    type="text"
                    className={`form-control ${
                      error.floor ? "is-invalid" : ""
                    }`}
                    name="floor"
                    value={parcel.floor}
                    onChange={handleChange}
                    placeholder="Enter floor number"
                  />
                  {error.floor && (
                    <div className="invalid-feedback">{error.floor}</div>
                  )}
                </div>
                <div className="mb-3">
                  <label className="form-label">Unit Number</label>
                  <input
                    type="text"
                    className={`form-control ${
                      error.unitNumber ? "is-invalid" : ""
                    }`}
                    name="unitNumber"
                    value={parcel.unitNumber}
                    onChange={handleChange}
                    placeholder="Enter unit number"
                  />
                  {error.unitNumber && (
                    <div className="invalid-feedback">{error.unitNumber}</div>
                  )}
                </div>
                <div className="d-grid gap-2 d-md-flex justify-content-md-end">
                  <button
                    type="button"
                    className="btn btn-secondary me-md-2"
                    onClick={() => navigate("/parcels")}
                  >
                    Cancel
                  </button>
                  <button type="submit" className="btn btn-primary">
                    Save Parcel
                  </button>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default ParcelComponent;
