import { Menubar } from "primereact/menubar";
import { Outlet } from "react-router-dom";

const items = [
  {
    id: "aluguel",
    label: "Aluguel",
    url: "/dashboard/aluguel-carros",
  },
  {
    id: "aluguel",
    label: "Carros",
    url: "/dashboard/gestao-carros",
  },
];

export function Dashboard() {
  return (
    <>
      <Menubar model={items} className="justify-center" />
      <div className="container mx-auto">
        <Outlet />
      </div>
    </>
  );
}
