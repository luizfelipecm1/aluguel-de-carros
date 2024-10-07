import { Button } from 'primereact/button';
import { InputText } from 'primereact/inputtext';
import React, { useState } from 'react';

interface CarFormProps {
    car?: any;
    onSave: (car: any) => void;
}

const CarForm: React.FC<CarFormProps> = ({ car, onSave }) => {
    const [formData, setFormData] = useState(car || { marca: '', modelo: '', ano: '', placa: '', matricula: '', imagens: [] });

    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleImageUrlChange = (e: React.ChangeEvent<HTMLInputElement>, index: number) => {
        const newUrls = [...formData.imagens];
        newUrls[index] = { url: e.target.value };
        setFormData({ ...formData, imagens: newUrls });
    };

    const handleAddImageUrl = () => {
        setFormData({ ...formData, imagens: [...formData.imagens, { url: '' }] });
    };

    const handleSubmit = (e: React.FormEvent) => {
        e.preventDefault();
        onSave(formData);
    };

    return (
        <div className='grid grid-cols-2 gap-4'>
            <form onSubmit={handleSubmit}>
                <InputText name="matricula" value={formData.matricula} onChange={handleChange} placeholder="Matrícula" />
                <InputText name="marca" value={formData.marca} onChange={handleChange} placeholder="Marca" />
                <InputText name="modelo" value={formData.modelo} onChange={handleChange} placeholder="Modelo" />
                <InputText name="ano" value={formData.ano} onChange={handleChange} placeholder="Ano" />
                <InputText name="placa" value={formData.placa} onChange={handleChange} placeholder="Placa" />

                <h3>URLs das Imagens</h3>
                {formData.imagens.map((image, index) => (
                    <InputText
                        key={index}
                        value={image.url}
                        onChange={(e) => handleImageUrlChange(e, index)}
                        placeholder={`URL da imagem ${index + 1}`}
                    />
                ))}
                <Button type="button" onClick={handleAddImageUrl}>Adicionar URL de Imagem</Button>

                <Button type="submit">Salvar</Button>
            </form>
        </div>
    );
};

export default CarForm;
