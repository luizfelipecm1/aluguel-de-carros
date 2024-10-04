import { Button } from "primereact/button"
import { Card } from "primereact/card"
import { DataView } from "primereact/dataview"
import { Dialog } from "primereact/dialog"
import { Dropdown } from "primereact/dropdown"
import { InputText } from "primereact/inputtext"
import { useState } from "react"

const carOptions = ["Celta"]
const agentOptions = ["XYZ"]
const rentals = [
  { car: "Celta", time: 600, agent: "XYZ", value: 1000 },
  { car: "Porsche", time: 600, agent: "XYZ", value: 1000 },
]

const itemTemplate = (data: any, layout: any) => {
  if (layout === "list") {
    return
  }

  if (layout === "grid") {
    return (
      <Card title={data.car} className="mt-4">
        <div className="w-full flex justify-between items-center">
          <div>
            <p>
              <span className="font-bold">Tempo: </span>
              {data.time}
            </p>
            <p>
              <span className="font-bold">Agente: </span>
              {data.agent}
            </p>
          </div>
          <div>
            <p>R$ {data.value}</p>

            <Button icon="pi pi-shopping-cart" label="Alugar" />
          </div>
        </div>
      </Card>
    )
  }
}

export function CarRental() {
  const [visible, setVisible] = useState(false)
  const [selectedCar, setSelectedCar] = useState(null)
  const [selectedAgent, setselectedAgent] = useState(null)

  function cleanFields() {
    setSelectedCar(null)
    setselectedAgent(null)
  }

  return (
    <>
      <div className="grid space-y-4">
        <div className="flex justify-between py-4 items-center">
          <h1 className="font-bold text-2xl">Aluguel de Carros</h1>
          <Button label="Criar pedido" onClick={() => setVisible(true)} />
        </div>

        <DataView value={rentals} layout="grid" itemTemplate={itemTemplate} />
      </div>

      <Dialog
        header="Criar pedido de alguel"
        visible={visible}
        style={{ width: "50vw" }}
        onHide={() => {
          if (!visible) return
          cleanFields()
          setVisible(false)
        }}
      >
        <form action="">
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
              <label htmlFor="time" className="font-bold">
                Tempo
              </label>
              <InputText id="time" />
            </div>
            <div className="grid gap-1">
              <label htmlFor="agents" className="font-bold">
                Agente
              </label>
              <Dropdown
                id="agents"
                value={selectedAgent}
                options={agentOptions}
                onChange={(e) => setselectedAgent(e.value)}
              />
            </div>
            <div className="grid gap-1">
              <label htmlFor="value" className="font-bold">
                Valor
              </label>
              <InputText id="value" />
            </div>
          </div>
          <Button label="Salvar" className="mt-4 float-end" />
        </form>
      </Dialog>
    </>
  )
}
