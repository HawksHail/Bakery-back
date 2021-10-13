export const getAllProducts = () => {
	return fetch("http://localhost:8091/product").then(res => res.json());
};
