import { Button } from 'primereact/button';
import { InputText } from 'primereact/inputtext';
import React, { useState } from 'react';
import { InputMask } from 'primereact/inputmask';

interface CarFormProps {
  car?: any;
  onSave: (car: any) => void;
}

const CarForm: React.FC<CarFormProps> = ({ car, onSave }) => {
  const [formData, setFormData] = useState(car || { make: '', model: '', year: '', placa: ''});

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    onSave(formData);
  };

  return (
    <form onSubmit={handleSubmit}>
      <InputText name="make" value={formData.make} onChange={handleChange} placeholder="Marca" />
      <InputText name="model" value={formData.model} onChange={handleChange} placeholder="Modelo" />
      <InputText name="year" value={formData.year} onChange={handleChange} placeholder="Ano" />
      <label htmlFor="placa" className="font-bold block mb-2"></label>
      <InputText name="placa" value={formData.placa} onChange={handleChange} id="placa" placeholder="Placa"></InputText>
      <Button type="submit">Save</Button>
    </form>
  );
};

export default CarForm;