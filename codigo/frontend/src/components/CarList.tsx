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

  return (
    <div className=''>
      <DataTable value={cars} tableStyle={{ minWidth: '50rem' }}>
        <Column field="make" header="Marca"></Column>
        <Column field="model" header="Modelo"></Column>
        <Column field="placa" header="Placa"></Column>
        <Column field="year" header="Ano"></Column>
        <Column body={actionBodyTemplate} header="Ações"></Column>
      </DataTable>

      <Dialog header="Editar Carro" visible={editDialogVisible} style={{ width: '50vw' }} onHide={hideDialog}>
        {selectedCar && (
          <div>
            <div className="p-field">
              <label htmlFor="make">Marca</label>
              <InputText id="make" value={selectedCar.make} onChange={(e) => setSelectedCar({ ...selectedCar, make: e.target.value })} />
            </div>
            <div className="p-field">
              <label htmlFor="model">Modelo</label>
              <InputText id="model" value={selectedCar.model} onChange={(e) => setSelectedCar({ ...selectedCar, model: e.target.value })} />
            </div>
            <div className="p-field">
              <label htmlFor="placa">Placa</label>
              <InputText id="placa" value={selectedCar.placa} onChange={(e) => setSelectedCar({ ...selectedCar, placa: e.target.value })} />
            </div>
            <div className="p-field">
              <label htmlFor="year">Ano</label>
              <InputText id="year" value={selectedCar.year} onChange={(e) => setSelectedCar({ ...selectedCar, year: e.target.value })} />
            </div>
            <Button label="Salvar" onClick={saveCar} />
          </div>
        )}
      </Dialog>
    </div>
  );
};

export default CarList;