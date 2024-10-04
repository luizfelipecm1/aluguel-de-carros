import { Button } from "primereact/button";
import { InputText } from "primereact/inputtext";
import { SelectButton } from "primereact/selectbutton";

export function Register() {
  return (
    <div className="container mx-auto h-screen grid place-content-center text-center gap-4">
      <h1 className="text-3xl font-bold">Cadastro</h1>
      <div className="p-inputgroup flex-1">
        <span className="p-inputgroup-addon">
          <i className="pi pi-user"></i>
        </span>
        <InputText placeholder="Nome" />
      </div>
      <div className="p-inputgroup flex-1">
        <span className="p-inputgroup-addon">
          <i className="pi pi-at"></i>
        </span>
        <InputText placeholder="E-mail" />
      </div>

      <div className="p-inputgroup flex-1">
        <span className="p-inputgroup-addon">
          <i className="pi pi-lock"></i>
        </span>
        <InputText placeholder="Senha" />
      </div>
      <SelectButton
        value={""}
        onChange={() => {}}
        options={["Cliente", "Agente"]}
        className="text-start"
      />
      <Button label="Salvar" />
    </div>
  );
}
