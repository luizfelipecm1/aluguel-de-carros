import { PrimeReactProvider } from "primereact/api";
import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import "./index.css";
import { Aluguel } from "./pages/aluguel";
import { GestaoCarros } from "./pages/gestao-carros";
import { Login } from "./pages/login";

const router = createBrowserRouter([
  {
    path: "/",
    element: <Login />,
  },
  {
    path: "/aluguel",
    element: <Aluguel />,
  },
  {
    path: "/gestao-carros",
    element: <GestaoCarros />,
  },
]);

createRoot(document.getElementById("root")!).render(
  <StrictMode>
    <PrimeReactProvider>
      <RouterProvider router={router} />
    </PrimeReactProvider>
  </StrictMode>
);
