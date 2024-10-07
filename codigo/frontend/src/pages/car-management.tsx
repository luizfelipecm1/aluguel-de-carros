import axios from 'axios';
import { Button } from 'primereact/button';
import { Column } from 'primereact/column';
import { DataTable } from 'primereact/datatable';
import { InputText } from 'primereact/inputtext';
import React, { useEffect, useState } from 'react';
import CarForm from '../components/CarForm';
import CarList from '../components/CarList';

export const CarManagement: React.FC = () => {
    const [cars, setCars] = useState<any[]>([]);
    const [editingCar, setEditingCar] = useState<any | null>(null);

    useEffect(() => {
        axios.get('http://localhost:8080/auto')
            .then(response => {
                console.log(response.data);
                setCars(response.data);
            })
            .catch(error => console.error('Error fetching data:', error));
    }, []);

    const handleSave = async (car: any) => {
        try {
            const response = await axios.post('http://localhost:8080/auto', car);
            setCars([...cars, response.data]); // Atualiza a lista de carros com o novo carro salvo
            alert("Veículo inserido com sucesso!!")
        } catch (error) {
            console.error('Erro ao salvar carro:', error);
        }
    };

    const handleEditCar = (updatedCar: { id: any; }) => {
        setCars(cars.map(car => car.id === updatedCar.id ? updatedCar : car));
    };

    const handleDeleteCar = (id: any) => {
        setCars(cars.filter(car => car.id !== id));
    };

    return (
        <div className="grid grid-cols-1 gap-10">
            <h1>Gestão de Carros</h1>
            <CarForm car={editingCar} onSave={handleSave} />
            <CarList cars={cars} onEdit={handleEditCar} onDelete={handleDeleteCar} />
        </div>
    );
};
