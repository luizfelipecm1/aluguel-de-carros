import axios from "axios";
import { Button } from "primereact/button";
import { InputText } from "primereact/inputtext";
import { useEffect, useState } from "react";

export function Login() {
  const [users, setUsers] = useState<any[]>([]);
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  useEffect(() => {
    axios.get('http://localhost:8080/usuarios')
        .then(response => {
          console.log(response.data); // Log data to check fields
          setUsers(response.data);
        })
        .catch(error => console.error('Error fetching data:', error));
  }, []);

  const handleLogin = () => {
    const user = users.find(user => user.nome === email && user.cpf === password);
    if (user) {
      console.log("Login successful");
      window.open("/Dashboard")
    } else {
        alert('Senha inválida')
      console.error("Invalid credentials");
    }
  };

  return (
    <div className="container mx-auto h-screen grid place-content-center text-center gap-4">
      <h1 className="text-3xl font-bold">Login</h1>
      <div className="p-inputgroup flex-1">
        <span className="p-inputgroup-addon">
          <i className="pi pi-at"></i>
        </span>
        <InputText 
          placeholder="E-mail" 
          value={email}
          onChange={(e) => setEmail(e.target.value)} 
        />
      </div>
      <div className="p-inputgroup flex-1">
        <span className="p-inputgroup-addon">
          <i className="pi pi-lock"></i>
        </span>
        <InputText 
          placeholder="Senha"
          type="password"
          value={password}
          onChange={(e) => setPassword(e.target.value)} 
        />
      </div>
      <Button label="Entrar" onClick={handleLogin} />
      <Button
        label="Cadastrar"
        link
        onClick={() => window.open("/cadastro", "_self")}
      />
    </div>
  );
}
