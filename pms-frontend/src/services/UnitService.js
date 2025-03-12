import axios from "axios";

const UNIT_API_BASE_URL = "http://localhost:8051/api/units";

export const getUnits = () => axios.get(UNIT_API_BASE_URL);

export const getUnitsById = (unitId) =>
  axios.get(`${UNIT_API_BASE_URL}/${unitId}`);

export const getAllUnits = () => axios.get(UNIT_API_BASE_URL);

export const createUnit = (unit) => axios.post(UNIT_API_BASE_URL, unit);

export const updateUnit = (unitId, unit) =>
  axios.put(`${UNIT_API_BASE_URL}/${unitId}`, unit);

export const deleteUnit = (unitId) =>
  axios.delete(`${UNIT_API_BASE_URL}/${unitId}`);
