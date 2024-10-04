import { DataTable } from 'primereact/datatable';
import React from 'react';
import { Column } from 'primereact/column';
import { Button } from 'primereact/button';


interface CarListProps {
  cars: any[];
  onEdit: (car: any) => void;
  onDelete: (id: number) => void;
}

const CarList: React.FC<CarListProps> = ({ cars, onEdit, onDelete }) => {
    const actionBodyTemplate = (rowData: any) => {
        return (
          <div>
            <Button onClick={() => onEdit(rowData)}>Editar</Button>
            <Button onClick={() => onDelete(rowData.id)}>Deletar</Button>
          </div>
        );
      };
  return (
<div>
    <DataTable value={cars} tableStyle={{ minWidth: '   50rem' }}>
    <Column field="make" header="Marca"></Column>
      <Column field="model" header="Modelo"></Column>
      <Column field="placa" header="Placa"></Column>
      <Column field="year" header="Ano"></Column>
      <Column body={actionBodyTemplate} header="Ações"></Column>
</DataTable>
    </div>
  );
};

export default CarList;