import React, { useState, useEffect } from 'react';
import { Button } from "primereact/button";
import { Card } from "primereact/card";
import { DataView } from "primereact/dataview";
import { Dialog } from "primereact/dialog";
import { Dropdown } from "primereact/dropdown";
import { InputText } from "primereact/inputtext";
import axios from 'axios';

const itemTemplate = (data: any, layout: any, onImageClick: (url: string, marca: string, modelo: string, ano: number) => void) => {
    if (layout === "list") {
        return;
    }

    if (layout === "grid") {
        return (
            <Card title={`Pedido ID: ${data.idPedido}`} className="mt-4">
                <div className="w-full flex justify-between items-center">
                    <div>
                        <p>
                            <span className="font-bold">Status: </span>
                            {data.status}
                        </p>
                        <p>
                            <span className="font-bold">Cliente: </span>
                            {data.cliente.nome}
                        </p>
                        <p>
                            <span className="font-bold">Carro: </span>
                            {data.automovel.marca} {data.automovel.modelo} - {data.automovel.placa}
                        </p>
                    </div>
                    <div className="flex gap-2 ml-4 items-center">
                        {data.automovel.imagens && data.automovel.imagens.map((imagem: any, index: number) => (
                            <img
                                key={index}
                                src={imagem.url}
                                alt={`Imagem do carro ${data.automovel.marca}`}
                                className="w-24 h-16 object-cover cursor-pointer"
                                onClick={() => onImageClick(imagem.url, data.automovel.marca, data.automovel.modelo, data.automovel.ano)}
                            />
                        ))}
                    </div>
                </div>
                <div className="flex justify-end mt-2">
                    <Button icon="pi pi-shopping-cart" label="Alugar" />
                </div>
            </Card>
        );
    }
};

export function CarRental() {
    const [visible, setVisible] = useState(false);
    const [selectedCar, setSelectedCar] = useState<string | null>(null);
    const [selectedAgent, setSelectedAgent] = useState<string | null>(null);
    const [status, setStatus] = useState<string>('PENDENTE'); // Novo estado para o status
    const [carOptions, setCarOptions] = useState<any[]>([]);
    const [agentOptions, setAgentOptions] = useState<any[]>([]);
    const [rentals, setRentals] = useState<any[]>([]);
    const [selectedImage, setSelectedImage] = useState<string | null>(null);
    const [imageDetails, setImageDetails] = useState<{ marca: string; modelo: string; ano: number } | null>(null);

    useEffect(() => {
        // Fetch para carros
        axios.get('http://localhost:8080/auto')
            .then(response => {
                setCarOptions(response.data.map(car => ({ label: car.modelo, value: car.matricula })));
            })
            .catch(error => {
                console.error('Erro ao buscar dados de carros:', error);
            });

        // Fetch para agentes
        axios.get('http://localhost:8080/usuarios')
            .then(response => {
                setAgentOptions(response.data.map(agent => ({ label: agent.nome, value: agent.id })));
            })
            .catch(error => {
                console.error('Erro ao buscar dados de agentes:', error);
            });

        // Fetch para pedidos/alugueis
        axios.get('http://localhost:8080/pedidos')
            .then(response => {
                setRentals(response.data);
            })
            .catch(error => {
                console.error('Erro ao buscar pedidos:', error);
            });

    }, []);

    function cleanFields() {
        setSelectedCar(null);
        setSelectedAgent(null);
        setStatus('PENDENTE'); // Resetar status para o padrão
    }

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();

        // Certifique-se de que selectedCar não seja nulo
        if (!selectedCar) {
            console.error('Carro não selecionado');
            return;
        }

        const newRental = {
            status: status, // Use o status selecionado
            cliente_id: 12345, // Substitua pelo ID do cliente real
            automovel_id: selectedCar, // Use automovel_id ao invés de automovel_matricula
            value: 1000 // Valor do aluguel
        };

        try {
            console.log(JSON.stringify(newRental));
            await axios.post('http://localhost:8080/pedidos', newRental);
            cleanFields();
            setVisible(false);

            // Atualizar lista de pedidos/alugueis após a criação
            const response = await axios.get('http://localhost:8080/pedidos');
            setRentals(response.data);
        } catch (error) {
            console.error('Erro ao criar pedido:', error);
        }
    };

    const onImageClick = (url: string, marca: string, modelo: string, ano: number) => {
        setSelectedImage(url);
        setImageDetails({ marca, modelo, ano });
    };

    return (
        <>
            <div className="grid space-y-4">
                <div className="flex justify-between py-4 items-center">
                    <h1 className="font-bold text-2xl">Aluguel de Carros</h1>
                    <Button label="Criar pedido" onClick={() => setVisible(true)} />
                </div>

                <DataView value={rentals} layout="grid" itemTemplate={(data, layout) => itemTemplate(data, layout, onImageClick)} />

                <Dialog
                    header={`${imageDetails?.marca} ${imageDetails?.modelo} - Ano: ${imageDetails?.ano}`}
                    visible={!!selectedImage}
                    onHide={() => {
                        setSelectedImage(null);
                        setImageDetails(null);
                    }}
                    style={{ width: "50vw" }}
                >
                    {selectedImage && (
                        <img src={selectedImage} alt="Imagem Ampliada" className="w-full h-auto" />
                    )}
                </Dialog>
            </div>

            <Dialog
                header="Criar pedido de aluguel"
                visible={visible}
                style={{ width: "50vw" }}
                onHide={() => {
                    cleanFields();
                    setVisible(false);
                }}
            >
                <form onSubmit={handleSubmit}>
                    <div className="grid grid-cols-2 gap-4">
                        <div className="grid gap-1">
                            <label htmlFor="cars" className="font-bold">
                                Carro
                            </label>
                            <Dropdown
                                id="cars"
                                value={selectedCar}
                                options={carOptions}
                                onChange={(e) => setSelectedCar(e.value)}
                            />
                        </div>
                        <div className="grid gap-1">
                            <label htmlFor="agents" className="font-bold">
                                Agente
                            </label>
                            <Dropdown
                                id="agents"
                                value={selectedAgent}
                                options={agentOptions}
                                onChange={(e) => setSelectedAgent(e.value)}
                            />
                        </div>
                        <div className="grid gap-1">
                            <label htmlFor="status" className="font-bold">
                                Status
                            </label>
                            <Dropdown
                                id="status"
                                value={status}
                                options={[
                                    { label: 'PENDENTE', value: 'PENDENTE' },
                                    { label: 'CONFIRMADO', value: 'CONFIRMADO' },
                                    { label: 'CANCELADO', value: 'CANCELADO' },
                                    { label: 'FINALIZADO', value: 'FINALIZADO' },
                                ]}
                                onChange={(e) => setStatus(e.value)}
                            />

                        </div>
                        <div className="grid gap-1">
                            <label htmlFor="value" className="font-bold">
                                Valor
                            </label>
                            <InputText id="value" value="1000" readOnly />
                        </div>
                    </div>
                    <Button type="submit" label="Salvar" className="mt-4 float-end" />
                </form>
            </Dialog>
        </>
    );
}
