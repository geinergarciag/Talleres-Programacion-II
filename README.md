# taller1P2
Evaluación de conocimientos hasta el corte 2

---

## 🏦 Sistema de Gestión Bancaria (SGB)

**Asignatura:** Programación Orientada a Objetos  
**Tema:** Clases Abstractas, Interfaces, Excepciones, Herencia y Polimorfismo  
**Grupo:** 4 integrantes  

---

## 👤 Datos del Grupo

| Integrante | Rol |
|---|---|
| Sebastian De Jesus Berrio | Desarrollo backend / Excepciones / Clases abstractas |
| Marcos Torrealba | Diagramas UML |
| Alfonso Acosta | Clases de Cuentas |
| Geiner Garcia | Clases de Empleados |

---

## 📋 Objetivo de Aprendizaje

Desarrollar una aplicación Java que integre los siguientes conceptos de POO:

- Clases abstractas con métodos abstractos y concretos
- Interfaces y contratos de comportamiento
- Excepciones personalizadas (checked y unchecked)
- Herencia en múltiples niveles
- Polimorfismo (sobrescritura y sobrecarga)
- Encapsulamiento con validaciones en setters
- Arrays estáticos de tamaño fijo para gestión de datos
- Diagramas UML (Clases y Casos de Uso)

---

## 📖 Historias de Usuario (Product Backlog)

| ID | Historia | Criterios de Aceptación | Prioridad | Story Points | Estado |
|---|---|---|---|---|---|
| HU-01 | Como cajero, quiero registrar un cliente natural con sus datos personales para aperturar una cuenta en el sistema | El sistema valida que el documento no esté repetido. Lanza `CapacidadExcedidaException` si se alcanzó el límite. Datos nulos lanzan `DatoInvalidoException` | Alta | 3 | ✅ Completado |
| HU-02 | Como cajero, quiero registrar un cliente empresarial con NIT y razón social | El sistema valida NIT no nulo. Se asocia correctamente al banco. Lanza `CapacidadExcedidaException` si hay más de 200 clientes | Alta | 3 | ✅ Completado |
| HU-03 | Como cajero, quiero abrir una cuenta corriente para un cliente | El sistema asocia la cuenta al cliente. Máximo 5 cuentas por cliente. Lanza `ClienteNoEncontradoException` si el cliente no existe | Alta | 3 | ✅ Completado |
| HU-04 | Como cajero, quiero abrir una cuenta de ahorros para un cliente | Igual que HU-03 pero para cuenta ahorros con tasa de interés configurable | Alta | 2 | ✅ Completado |
| HU-05 | Como cajero, quiero abrir una cuenta de crédito para un cliente | Igual que HU-03 pero para cuenta crédito con límite de crédito configurable | Alta | 2 | ✅ Completado |
| HU-06 | Como cajero, quiero realizar depósitos en cuentas activas | El sistema valida que la cuenta no esté bloqueada. Lanza `CuentaBloqueadaException` si está bloqueada. El monto debe ser mayor a 0 | Alta | 3 | ✅ Completado |
| HU-07 | Como cajero, quiero realizar retiros validando el saldo disponible | El sistema valida saldo suficiente. Lanza `SaldoInsuficienteException` con saldo actual y monto solicitado. Lanza `CuentaBloqueadaException` si está bloqueada | Alta | 3 | ✅ Completado |
| HU-08 | Como asesor financiero, quiero realizar transferencias entre dos cuentas | El sistema debita de la cuenta origen y acredita en la cuenta destino. Valida saldo y estado de ambas cuentas | Alta | 5 | ✅ Completado |
| HU-09 | Como asesor financiero, quiero buscar un cliente por su ID | El sistema retorna el cliente si existe. Lanza `ClienteNoEncontradoException` si no lo encuentra | Media | 2 | ✅ Completado |
| HU-10 | Como asesor financiero, quiero cambiar el estado de una transacción | El sistema valida la transición. Lanza `EstadoTransaccionInvalidoException` si la transición no es válida | Alta | 3 | ✅ Completado |
| HU-11 | Como asesor financiero, quiero bloquear y desbloquear cuentas | El sistema cambia el estado de bloqueada a la cuenta indicada | Media | 2 | ✅ Completado |
| HU-12 | Como gerente, quiero aprobar créditos | Solo el gerente puede aprobar créditos. Si otro tipo de empleado lo intenta, lanza `PermisoInsuficienteException` | Alta | 3 | ✅ Completado |
| HU-13 | Como gerente, quiero calcular la nómina total del banco | El sistema suma los salarios de todos los empleados usando polimorfismo | Alta | 3 | ✅ Completado |
| HU-14 | Como gerente, quiero registrar empleados (cajeros, asesores, gerentes) | El sistema valida que no se supere el límite de 50 empleados. Lanza `CapacidadExcedidaException` si se supera | Alta | 2 | ✅ Completado |
| HU-15 | Como sistema, quiero calcular intereses mensuales de todas las cuentas | El sistema recorre todas las cuentas y aplica la fórmula de interés según el tipo usando polimorfismo | Media | 3 | ✅ Completado |
| HU-16 | Como cliente, quiero recibir notificaciones del banco | El sistema notifica solo si el cliente tiene activadas las notificaciones. Implementa interfaz `Notificable` | Baja | 2 | ✅ Completado |
| HU-17 | Como sistema auditor, quiero consultar el historial de modificaciones de una entidad | El sistema registra fecha y usuario de modificación. Implementa interfaz `Auditable` | Media | 2 | ✅ Completado |
| HU-18 | Como sistema, quiero calcular el salario de cada empleado según su tipo | Cajero: salario base + bono por transacciones. Asesor: salario base + comisión si supera meta. Gerente: salario base + antigüedad + bono fijo | Alta | 5 | ✅ Completado |

---

## 🏗️ Estructura de Paquetes

```
SistemaBancario/
 └── src/
      ├── modelo/
      │    ├── abstractas/
      │    │    ├── Persona.java
      │    │    ├── Empleado.java
      │    │    └── Cuenta.java
      │    ├── personas/
      │    │    ├── ClienteNatural.java
      │    │    └── ClienteEmpresarial.java
      │    ├── empleados/
      │    │    ├── Cajero.java
      │    │    ├── AsesorFinanciero.java
      │    │    └── GerenteSucursal.java
      │    ├── cuentas/
      │    │    ├── CuentaCorriente.java
      │    │    ├── CuentaAhorros.java
      │    │    └── CuentaCredito.java
      │    ├── banco/
      │    │    ├── Banco.java
      │    │    └── Transaccion.java
      │    ├── interfaces/
      │    │    ├── Consultable.java
      │    │    ├── Transaccionable.java
      │    │    ├── Auditable.java
      │    │    └── Notificable.java
      │    ├── excepciones/
      │    │    ├── SistemaBancarioException.java
      │    │    ├── BancoRuntimeException.java
      │    │    ├── SaldoInsuficienteException.java
      │    │    ├── CuentaBloqueadaException.java
      │    │    ├── ClienteNoEncontradoException.java
      │    │    ├── CapacidadExcedidaException.java
      │    │    ├── DatoInvalidoException.java
      │    │    ├── EstadoTransaccionInvalidoException.java
      │    │    └── PermisoInsuficienteException.java
      │    └── enums/
      │         ├── EstadoTransaccion.java
      │         ├── TipoCuenta.java
      │         ├── Turno.java
      │         └── TipoDocumento.java
      └── principal/
           └── SistemaBancarioDemo.java
```

---

## 🔷 Jerarquía de Herencia

```
Persona (abstracta)
 ├── Empleado (abstracta)
 │    ├── Cajero
 │    ├── AsesorFinanciero
 │    └── GerenteSucursal
 └── Cliente (abstracta)
      ├── ClienteNatural
      └── ClienteEmpresarial

Cuenta (abstracta)
 ├── CuentaCorriente
 ├── CuentaAhorros
 └── CuentaCredito
```

---

## 🔴 Jerarquía de Excepciones

```
Exception
 └── SistemaBancarioException (checked)
      ├── SaldoInsuficienteException
      ├── CuentaBloqueadaException
      ├── ClienteNoEncontradoException
      └── CapacidadExcedidaException

RuntimeException
 └── BancoRuntimeException (unchecked)
      ├── DatoInvalidoException
      ├── EstadoTransaccionInvalidoException
      └── PermisoInsuficienteException
```

---

## 🔵 Interfaces del Sistema

| Interfaz | Implementada por |
|---|---|
| `Consultable` | ClienteNatural, ClienteEmpresarial, CuentaCorriente, CuentaAhorros, CuentaCredito, AsesorFinanciero, GerenteSucursal |
| `Transaccionable` | CuentaCorriente, CuentaAhorros, CuentaCredito |
| `Auditable` | ClienteNatural, ClienteEmpresarial, CuentaCorriente, CuentaAhorros, CuentaCredito, AsesorFinanciero, GerenteSucursal, Banco |
| `Notificable` | ClienteNatural, ClienteEmpresarial |

---

## 🟡 Enums del Sistema

| Enum | Valores |
|---|---|
| `EstadoTransaccion` | PENDIENTE, PROCESANDO, COMPLETADA, RECHAZADA, REVERTIDA |
| `TipoCuenta` | CORRIENTE, AHORROS, CREDITO |
| `Turno` | MANANA, TARDE, NOCHE |
| `TipoDocumento` | CEDULA, PASAPORTE, NIT, CEDULA_EXTRANJERIA |

---

## 🖥️ Escenarios Demostrados en la Clase Principal

| # | Escenario | Concepto evaluado | Estado |
|---|---|---|---|
| 1 | Registrar 2 clientes naturales y 1 empresarial | Herencia, constructores con super() | ✅ |
| 2 | Abrir una cuenta de cada tipo | Polimorfismo, arrays estáticos | ✅ |
| 3 | Depósito exitoso y CuentaBloqueadaException | Excepciones checked | ✅ |
| 4 | Retiro exitoso y SaldoInsuficienteException | Excepciones checked, atributos del error | ✅ |
| 5 | Transferencia entre dos cuentas | Interfaces Transaccionable | ✅ |
| 6 | Recorrer Empleado[] e imprimir calcularSalario() | Polimorfismo de sobrescritura | ✅ |
| 7 | Recorrer Cuenta[] e imprimir calcularInteres() | Polimorfismo de sobrescritura | ✅ |
| 8 | Transición inválida y EstadoTransaccionInvalidoException | Excepciones unchecked | ✅ |
| 9 | Cajero aprueba crédito y PermisoInsuficienteException | Excepciones unchecked, control de acceso | ✅ |
| 10 | Notificar a cliente que acepta y a uno que no | Interfaces Notificable | ✅ |
| 11 | registrarModificacion() y consultar auditoría | Interfaces Auditable | ✅ |
| 12 | Calcular nómina total del banco | Polimorfismo, composición | ✅ |

---

## ✅ Lista de Verificación Pre-Entrega

- [x] Las clases `Persona`, `Empleado` y `Cuenta` son abstractas y tienen al menos un método abstracto cada una
- [x] Todas las clases concretas implementan los métodos abstractos de sus clases padre
- [x] Todas las clases hijas usan `super()` en su constructor
- [x] Los getters de arrays retornan una copia con `System.arraycopy()` — nunca el array original
- [x] Las 4 interfaces tienen implementación real en todas las clases indicadas — sin cuerpos vacíos `{}`
- [x] Existe la jerarquía de excepciones checked con base en `SistemaBancarioException`
- [x] Existe la jerarquía de excepciones unchecked con base en `BancoRuntimeException`
- [x] Los setters lanzan `DatoInvalidoException` ante datos nulos, vacíos o fuera de rango
- [x] `cambiarEstado()` en `Transaccion` lanza `EstadoTransaccionInvalidoException` en transiciones inválidas
- [x] La clase principal demuestra todos los 12 escenarios
- [ ] Los diagramas UML coinciden con el código implementado
- [x] El código compila y ejecuta sin errores

---

## 🚀 Instrucciones para Ejecutar

1. Clonar o descargar el repositorio
2. Abrir NetBeans
3. `File` → `Open Project` → seleccionar la carpeta `SistemaBancario`
4. Click derecho en el proyecto → `Clean and Build`
5. Click derecho en `SistemaBancarioDemo.java` → `Run File`

---

## 📁 Entregables

- [x] Código fuente Java organizado en la estructura de paquetes indicada
- [ ] Diagrama de Casos de Uso en `/docs`
- [ ] Diagrama de Clases en `/docs`
- [x] Este README.md con la tabla de historias de usuario completada
