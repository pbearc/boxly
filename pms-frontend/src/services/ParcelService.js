import axios from "axios";

const PARCEL_API_BASE_URL = "http://localhost:8051/api/parcels";

export const getParcels = () => axios.get(PARCEL_API_BASE_URL);

export const getParcelById = (parcelId) =>
  axios.get(`${PARCEL_API_BASE_URL}/${parcelId}`);

export const getAllParcels = () => axios.get(PARCEL_API_BASE_URL);

export const createParcel = (parcel) => axios.post(PARCEL_API_BASE_URL, parcel);

export const updateParcel = (parcelId, parcel) =>
  axios.put(`${PARCEL_API_BASE_URL}/${parcelId}`, parcel);

export const deleteParcel = (parcelId) =>
  axios.delete(`${PARCEL_API_BASE_URL}/${parcelId}`);

export const parseParcelInfo = (imageFormData) => {
  return axios.post("http://localhost:8051/api/ocr/parse", imageFormData, {
    headers: {
      "Content-Type": "multipart/form-data",
    },
  });
};
