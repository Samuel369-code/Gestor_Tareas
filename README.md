# Gestor_Tareas

Aplicación de escritorio desarrollada en Java SE para gestionar tareas de manera sencilla. Permite crear, editar, eliminar y visualizar tareas, asignándoles un estado (Pendiente, En Progreso, Completada) y una fecha. La interfaz muestra las tareas en una tabla y próximamente se puede integrar un calendario semanal para ver las tareas por día.

Tecnologías utilizadas:

Java SE (Java 17+)

Swing: para la interfaz gráfica de usuario (JFrame, JTable, JPanel, JButton, JOptionPane, JSpinner).

JDBC (Java Database Connectivity): conexión con base de datos y ejecución de consultas SQL.

PostgreSQL: base de datos relacional para almacenar las tareas.

Patrón DAO (Data Access Object): TareaDAO para separar la lógica de acceso a datos de la interfaz.

LocalDate (java.time): manejo de fechas moderno y seguro.

Renderizadores personalizados (TableCellRenderer): para colorear estados de las tareas y personalizar encabezados de la tabla.


Funcionalidades principales:

Crear nuevas tareas con título, descripción, estado y fecha.

Editar tareas existentes, incluyendo cambio de estado y fecha.

Eliminar tareas.

Visualizar todas las tareas en una tabla con colores según el estado.

Posibilidad de integrar un calendario semanal para visualizar tareas por día.
