document.addEventListener("DOMContentLoaded", function () {
    const cantidadBurbujas = 50;
    const contenedor = document.getElementById("yellow2");

    for (let i = 0; i < cantidadBurbujas; i++) {
        crearBurbuja();
    }

    function crearBurbuja() {
        const burbuja = document.createElement("div");
        burbuja.className = "burbuja";

        // Asignar posición aleatoria en la pantalla
        const posX = Math.random() ;
        const posY = Math.random() ;
        burbuja.style.left = `${posX}px`;
        burbuja.style.top = `${posY}px`;

        // Asignar tamaño aleatorio a la burbuja
        const tamano = Math.random() * 70 + 40; // Tamaño entre 10 y 40 pixels
        burbuja.style.width = `${tamano}px`;
        burbuja.style.height = `${tamano}px`;

        // Asignar velocidad de movimiento aleatoria
        const velocidad = Math.random() * 5 + 2; // Velocidad entre 2 y 7 segundos por ciclo
        burbuja.style.animationDuration = `${velocidad}s`;

        contenedor.appendChild(burbuja);
    }
});