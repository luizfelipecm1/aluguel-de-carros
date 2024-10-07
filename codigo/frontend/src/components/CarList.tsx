import React, { useState } from 'react';
import { DataTable } from 'primereact/datatable';
import { Column } from 'primereact/column';
import { Button } from 'primereact/button';
import { Dialog } from 'primereact/dialog';
import { InputText } from 'primereact/inputtext';

interface CarListProps {
  cars: any[];
  onEdit: (car: any) => void;
  onDelete: (id: number) => void;
}

const CarList: React.FC<CarListProps> = ({ cars, onEdit, onDelete }) => {
  const [selectedCar, setSelectedCar] = useState<any>(null);
  const [editDialogVisible, setEditDialogVisible] = useState(false);

  const actionBodyTemplate = (rowData: any) => {
    return (
        <div>
          <Button onClick={() => editCar(rowData)}>Editar</Button>
          <Button onClick={() => onDelete(rowData.id)}>Deletar</Button>
        </div>
    );
  };

  const editCar = (car: any) => {
    setSelectedCar(car);
    setEditDialogVisible(true);
  };

  const hideDialog = () => {
    setEditDialogVisible(false);
    setSelectedCar(null);
  };

  const saveCar = () => {
    onEdit(selectedCar);
    hideDialog();
  };

  const imagesBodyTemplate = (rowData: any) => {
    return (
        <div className="flex space-x-2">
          {rowData.imagens.map((image: string, index: number) => (
              <img
                  key={index}
                  src={image}
                  alt={`Car Image ${index}`}
                  className="h-20 w-20 object-cover rounded-md cursor-pointer" // Adjusted styles
                  onClick={() => window.open(image, '_blank')}
              />
          ))}
        </div>
    );
  };

  return (
      <div>
        <DataTable value={cars} tableStyle={{ minWidth: '50rem' }}>
          <Column field="marca" header="Marca"></Column>
          <Column field="modelo" header="Modelo"></Column>
          <Column field="placa" header="Placa"></Column>
          <Column field="ano" header="Ano"></Column>
          <Column body={imagesBodyTemplate} header="Imagens"></Column>
          <Column body={actionBodyTemplate} header="Ações"></Column>
        </DataTable>

        <Dialog header="Editar Carro" visible={editDialogVisible} style={{ width: '50vw' }} onHide={hideDialog}>
          {selectedCar && (
              <div className="grid grid-cols-2 gap-4">
                <div className="grid gap-1 p-field">
                  <label htmlFor="make">Marca</label>
                  <InputText id="make" value={selectedCar.marca} onChange={(e) => setSelectedCar({ ...selectedCar, marca: e.target.value })} />
                </div>
                <div className="grid gap-1 p-field">
                  <label htmlFor="model">Modelo</label>
                  <InputText id="model" value={selectedCar.modelo} onChange={(e) => setSelectedCar({ ...selectedCar, modelo: e.target.value })} />
                </div>
                <div className="grid gap-1 p-field">
                  <label htmlFor="placa">Placa</label>
                  <InputText id="placa" value={selectedCar.placa} onChange={(e) => setSelectedCar({ ...selectedCar, placa: e.target.value })} />
                </div>
                <div className="grid gap-1 p-field">
                  <label htmlFor="year">Ano</label>
                  <InputText id="year" value={selectedCar.ano} onChange={(e) => setSelectedCar({ ...selectedCar, ano: e.target.value })} />
                </div>
                <Button label="Salvar" onClick={saveCar} />
              </div>
          )}
        </Dialog>
      </div>
  );
};

export default CarList;
