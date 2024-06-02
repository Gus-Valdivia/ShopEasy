const alertsContainer = document.getElementById('alerts-container');

function mostrarAlerta(producto) {
    const alertDiv = document.createElement('div');
    alertDiv.classList.add('alert');
    alertDiv.classList.add('alert-info');
    alertDiv.innerHTML = `
        <img src="${producto.imagen}" alt="${producto.nombre}">
        <div>
            <strong>${producto.nombre}</strong><br>
            Cantidad: ${producto.cantidad}
        </div>
    `;
    alertsContainer.appendChild(alertDiv);

    // Elimina la alerta después de 5 segundos
    setTimeout(() => {
        alertDiv.classList.add('fade-out'); // Agregamos la clase para el desvanecido
        setTimeout(() => {
            alertDiv.remove(); // Eliminamos la alerta después de la transición
        }, 500); // Esperamos 500ms (tiempo de la transición)
    }, 5000); // Eliminamos la alerta después de 5 segundos
}
