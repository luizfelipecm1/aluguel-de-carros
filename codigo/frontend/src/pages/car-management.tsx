import { Button } from "primereact/button";
import { Column } from "primereact/column";
import { DataTable } from "primereact/datatable";
import { InputText } from "primereact/inputtext";
import React, { useEffect } from "react";
import { useState } from 'react';
import CarForm from '../components/CarForm';
import CarList from '../components/CarList';

  export const CarManagement: React.FC = () => {
    const [cars, setCars] = useState<any[]>([]);
    const [editingCar, setEditingCar] = useState<any | null>(null);
  
    const handleSave = (car: any) => {
      if (editingCar) {
        setCars(cars.map(c => (c.id === car.id ? car : c)));
      } else {
        setCars([...cars, { ...car, id: Date.now() }]);
      }
      setEditingCar(null);
    };
  
    const handleEdit = (car: any) => {
      setEditingCar(car);
    };
  
    const handleDelete = (id: number) => {
      setCars(cars.filter(car => car.id !== id));
    };
  
    return (
      <div className="grid grid-cols-1 gap-10">
        <h1>Gestão de Carros</h1>
        <CarForm car={editingCar} onSave={handleSave} />
        <CarList cars={cars} onEdit={handleEdit} onDelete={handleDelete} />
      </div>
    );
  };
