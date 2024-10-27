import React, { useState, useEffect } from 'react';
import { Button } from "primereact/button";
import { Card } from "primereact/card";
import { DataView } from "primereact/dataview";
import { Dialog } from "primereact/dialog";
import { Dropdown } from "primereact/dropdown";
import { InputText } from "primereact/inputtext";
import axios from 'axios';

// Template para exibir cada item no DataView
const itemTemplate = (data: any, layout: any, onImageClick: (url: string, marca: string, modelo: string, ano: number) => void) => {
    // O layout atual permite exibir os dados em forma de grade ou lista.
    // O layout de lista não está implementado, o que pode limitar a flexibilidade do componente.
    if (layout === "list") {
        return; // Considerar implementar ou remover esta condição se não for necessário.
    }

    if (layout === "grid") {
        return (
            <Card title={`Pedido ID: ${data.idPedido}`} className="mt-4">
                <div className="w-full flex justify-between items-center">
                    <div>
                        {/* Exibe informações do pedido, tornando claro o que está sendo exibido */}
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
                        {/* Renderiza imagens do carro, permitindo que o usuário clique e veja uma imagem ampliada */}
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
    const [visible, setVisible] = useState(false); // Controla a visibilidade do diálogo de criação de pedido
    const [selectedCar, setSelectedCar] = useState<string | null>(null); // Armazena o carro selecionado
    const [selectedAgent, setSelectedAgent] = useState<string | null>(null); // Armazena o agente selecionado
    const [status, setStatus] = useState<string>('PENDENTE'); // Estado inicial do status do pedido
    const [carOptions, setCarOptions] = useState<any[]>([]); // Opções de carros para o dropdown
    const [agentOptions, setAgentOptions] = useState<any[]>([]); // Opções de agentes para o dropdown
    const [rentals, setRentals] = useState<any[]>([]); // Lista de pedidos/alugueis
    const [selectedImage, setSelectedImage] = useState<string | null>(null); // Imagem selecionada para visualização
    const [imageDetails, setImageDetails] = useState<{ marca: string; modelo: string; ano: number } | null>(null); // Detalhes da imagem selecionada

    useEffect(() => {
        // O uso de funções assíncronas dentro de useEffect melhora a legibilidade e separa a lógica de busca.
        const fetchCars = async () => {
            try {
                const response = await axios.get('http://localhost:8080/auto');
                // Ao mapear os dados, a estrutura do objeto é mais clara para o dropdown
                setCarOptions(response.data.map(car => ({ label: car.modelo, value: car.matricula })));
            } catch (error) {
                console.error('Erro ao buscar dados de carros:', error);
                // Sugestão: Adicionar feedback visual para o usuário ao ocorrer um erro, como uma notificação de erro.
            }
        };

        const fetchAgents = async () => {
            try {
                const response = await axios.get('http://localhost:8080/usuarios');
                setAgentOptions(response.data.map(agent => ({ label: agent.nome, value: agent.id })));
            } catch (error) {
                console.error('Erro ao buscar dados de agentes:', error);
                // Sugestão: Adicionar feedback visual ao usuário.
            }
        };

        const fetchRentals = async () => {
            try {
                const response = await axios.get('http://localhost:8080/pedidos');
                setRentals(response.data);
            } catch (error) {
                console.error('Erro ao buscar pedidos:', error);
                // Sugestão: Adicionar feedback visual ao usuário.
            }
        };

        // Chama as funções para buscar os dados
        fetchCars();
        fetchAgents();
        fetchRentals();
    }, []);

    // Função para limpar os campos do formulário
    function cleanFields() {
        setSelectedCar(null);
        setSelectedAgent(null);
        setStatus('PENDENTE'); // Resetar status para o padrão
    }

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();

        // A validação da seleção do carro é importante para evitar erros ao enviar o formulário.
        if (!selectedCar) {
            console.error('Carro não selecionado');
            // Sugestão: Adicionar uma mensagem de erro visual para o usuário quando não houver carro selecionado.
            return;
        }

        const newRental = {
            status: status, // Estado selecionado
            cliente_id: 12345, // Substitua pelo ID do cliente real; considere usar um estado para gerenciar o ID do cliente.
            automovel_id: selectedCar, // Use automovel_id ao invés de automovel_matricula
            value: 1000 // Valor fixo do aluguel; considere torná-lo dinâmico para maior flexibilidade.
        };

        try {
            console.log(JSON.stringify(newRental));
            await axios.post('http://localhost:8080/pedidos', newRental);
            cleanFields(); // Limpa os campos após o envio
            setVisible(false); // Fecha o diálogo

            // Atualiza a lista de pedidos após a criação
            const response = await axios.get('http://localhost:8080/pedidos');
            setRentals(response.data);
        } catch (error) {
            console.error('Erro ao criar pedido:', error);
            // Sugestão: Adicionar feedback visual ao usuário em caso de erro ao criar o pedido.
        }
    };

    // Função chamada ao clicar em uma imagem para visualização
    const onImageClick = (url: string, marca: string, modelo: string, ano: number) => {
        setSelectedImage(url); // Armazena a imagem selecionada
        setImageDetails({ marca, modelo, ano }); // Armazena detalhes do carro
    };

    return (
        <>
            <div className="grid space-y-4">
                <div className="flex justify-between py-4 items-center">
                    <h1 className="font-bold text-2xl">Aluguel de Carros</h1>
                    <Button label="Criar pedido" onClick={() => setVisible(true)} />
                </div>

                {/* Exibe os pedidos em um DataView */}
                <DataView value={rentals} layout="grid" itemTemplate={(data, layout) => itemTemplate(data, layout, onImageClick)} />

                {/* Diálogo para exibir imagem ampliada */}
                <Dialog
                    header={`${imageDetails?.marca} ${imageDetails?.modelo} - Ano: ${imageDetails?.ano}`}
                    visible={!!selectedImage}
                    onHide={() => {
                        setSelectedImage(null); // Limpa a imagem selecionada ao fechar
                        setImageDetails(null); // Limpa os detalhes ao fechar
                    }}
                    style={{ width: "50vw" }}
                >
                    {selectedImage && (
                        <img src={selectedImage} alt="Imagem Ampliada" className="w-full h-auto" />
                    )}
                </Dialog>
            </div>

            {/* Diálogo para criar um novo pedido de aluguel */}
            <Dialog
                header="Criar pedido de aluguel"
                visible={visible}
                style={{ width: "50vw" }}
                onHide={() => {
                    cleanFields(); // Limpa campos ao fechar
                    setVisible(false); // Fecha o diálogo
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
                                onChange={(e) => setSelectedCar(e.value)} // Atualiza o carro selecionado
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
                                onChange={(e) => setSelectedAgent(e.value)} // Atualiza o agente selecionado
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
                                onChange={(e) => setStatus(e.value)} // Atualiza o status selecionado
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
