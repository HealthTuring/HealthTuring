// src/app/mocks/tratamientos.mock.ts

export const TRATAMIENTOS_MOCK = {
  tratamientos: [
    {
      id_tratamiento: "T001",
      medicamento: "Amoxicilina",
      dosis: "500 mg",
      frecuencia: "3 veces al día",
      duracion: "7 días",
      via: "Oral",
      inicio: "2025-05-15",
      fin: "2025-05-22",
      indicaciones: "Infección respiratoria",
      efectos_secundarios: ["Náuseas", "Diarrea"],
      incompatibilidades: ["Metotrexato", "Alopurinol"]
    },
    {
      id_tratamiento: "T002",
      medicamento: "Ibuprofeno",
      dosis: "400 mg",
      frecuencia: "Cada 8 horas",
      duracion: "5 días",
      via: "Oral",
      inicio: "2025-05-15",
      fin: "2025-05-20",
      indicaciones: "Dolor muscular",
      efectos_secundarios: ["Irritación gástrica", "Mareos"],
      incompatibilidades: ["Aspirina", "Warfarina"]
    },
    {
      id_tratamiento: "T003",
      medicamento: "Insulina glargina",
      dosis: "10 unidades",
      frecuencia: "1 vez al día",
      duracion: "Indefinido",
      via: "Subcutánea",
      inicio: "2025-01-01",
      fin: null,
      indicaciones: "Diabetes tipo 2",
      efectos_secundarios: ["Hipoglucemia", "Reacción en el sitio de inyección"],
      incompatibilidades: ["Alcohol", "Propranolol"]
    },
    {
      id_tratamiento: "T004",
      medicamento: "Metformina",
      dosis: "850 mg",
      frecuencia: "2 veces al día",
      duracion: "Indefinido",
      via: "Oral",
      inicio: "2024-12-01",
      fin: null,
      indicaciones: "Diabetes tipo 2",
      efectos_secundarios: ["Malestar estomacal", "Diarrea"],
      incompatibilidades: ["Yodo radioactivo", "Cimetidina"]
    },
    {
      id_tratamiento: "T005",
      medicamento: "Lisinopril",
      dosis: "10 mg",
      frecuencia: "1 vez al día",
      duracion: "Indefinido",
      via: "Oral",
      inicio: "2025-03-10",
      fin: null,
      indicaciones: "Hipertensión",
      efectos_secundarios: ["Tos seca", "Mareos"],
      incompatibilidades: ["Diuréticos ahorradores de potasio", "Sales de potasio"]
    },
    {
      id_tratamiento: "T006",
      medicamento: "Omeprazol",
      dosis: "20 mg",
      frecuencia: "1 vez al día",
      duracion: "14 días",
      via: "Oral",
      inicio: "2025-05-10",
      fin: "2025-05-24",
      indicaciones: "Reflujo gastroesofágico",
      efectos_secundarios: ["Dolor de cabeza", "Diarrea"],
      incompatibilidades: ["Clopidogrel", "Ketoconazol"]
    },
    {
      id_tratamiento: "T007",
      medicamento: "Atorvastatina",
      dosis: "20 mg",
      frecuencia: "1 vez al día",
      duracion: "Indefinido",
      via: "Oral",
      inicio: "2025-02-01",
      fin: null,
      indicaciones: "Colesterol alto",
      efectos_secundarios: ["Dolor muscular", "Elevación de enzimas hepáticas"],
      incompatibilidades: ["Jugo de toronja", "Ciclosporina"]
    },
    {
      id_tratamiento: "T008",
      medicamento: "Warfarina",
      dosis: "5 mg",
      frecuencia: "1 vez al día",
      duracion: "Indefinido",
      via: "Oral",
      inicio: "2025-01-20",
      fin: null,
      indicaciones: "Prevención de trombos",
      efectos_secundarios: ["Hemorragias", "Moretones"],
      incompatibilidades: ["Aspirina", "Vitamina K"]
    },
    {
      id_tratamiento: "T009",
      medicamento: "Salbutamol",
      dosis: "2 inhalaciones",
      frecuencia: "Cada 6 horas según necesidad",
      duracion: "Según síntomas",
      via: "Inhalatoria",
      inicio: "2025-05-10",
      fin: null,
      indicaciones: "Asma",
      efectos_secundarios: ["Temblor", "Taquicardia"],
      incompatibilidades: ["Propranolol", "Metoprolol"]
    },
    {
      id_tratamiento: "T010",
      medicamento: "Diazepam",
      dosis: "5 mg",
      frecuencia: "Antes de dormir",
      duracion: "10 días",
      via: "Oral",
      inicio: "2025-05-12",
      fin: "2025-05-22",
      indicaciones: "Ansiedad leve",
      efectos_secundarios: ["Somnolencia", "Dependencia"],
      incompatibilidades: ["Alcohol", "Opioides"]
    }
  ]
};
