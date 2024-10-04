import { Button } from "primereact/button";
import { InputText } from "primereact/inputtext";

export function Login() {
  return (
    <div className="container mx-auto h-screen grid place-content-center text-center gap-4">
      <h1 className="text-3xl font-bold">Login</h1>
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
      <Button label="Entrar" />
      <Button
        label="Cadastrar"
        link
        onClick={() => window.open("/cadastro", "_self")}
      />
    </div>
  );
}
