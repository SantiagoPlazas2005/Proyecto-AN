// URL base de la API
const apiUrl = "http://localhost:8080/api/Canchas";  // Cambia esta URL por la de tu API

// Función para crear o actualizar una cancha
function createOrUpdate() {
    const id = $('#id').val();
    const nombre = $('#nombre').val();
    const ubicacion = $('#ubicacion').val();
    const horariosDisponibles = $('#horariosDisponibles').val();

    const data = {
        nombre: nombre,
        ubicacion: ubicacion,
        horariosDisponibles: horariosDisponibles
    };

    if (id) {
        // Si el id está presente, realiza un PUT (actualización)
        $.ajax({
            url: `${apiUrl}/${id}`,
            method: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function(response) {
                alert("Cancha actualizada exitosamente!");
                resetForm();
                loadCanchas();  // Recargar las canchas después de la actualización
            },
            error: function(xhr, status, error) {
                alert("Error al actualizar la cancha: " + error);
            }
        });
    } else {
        // Si no hay id, realiza un POST (creación)
        $.ajax({
            url: apiUrl,
            method: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function(response) {
                alert("Cancha creada exitosamente!");
                resetForm();
                loadCanchas();  // Recargar las canchas después de la creación
            },
            error: function(xhr, status, error) {
                alert("Error al crear la cancha: " + error);
            }
        });
    }
}

// Función para cargar las canchas desde la API
function loadCanchas() {
    $.ajax({
        url: apiUrl,
        method: 'GET',
        success: function(response) {
            let html = '';
            response.forEach(canchas => {
                html += `
                    <tr>
                        <td>${canchas.id}</td>
                        <td>${canchas.nombre}</td>
                        <td>${canchas.ubicacion}</td>
                        <td>${canchas.horariosDisponibles}</td>
                        <td>
                            <button class="btn btn-warning" onclick="editCancha(${canchas.id})">Editar</button>
                            <button class="btn btn-danger" onclick="deleteCancha(${canchas.id})">Eliminar</button>
                        </td>
                    </tr>
                `;
            });
            $('#dataBody').html(html);
        },
        error: function(xhr, status, error) {
            alert("Error al cargar las canchas: " + error);
        }
    });
}

// Función para editar una cancha
function editCancha(id) {
    $.ajax({
        url: `${apiUrl}/${id}`,
        method: 'GET',
        success: function(response) {
            $('#id').val(response.id);
            $('#nombre').val(response.nombre);
            $('#ubicacion').val(response.ubicacion);
            $('#horariosDisponibles').val(response.horariosDisponibles);
        },
        error: function(xhr, status, error) {
            alert("Error al obtener los datos de la cancha: " + error);
        }
    });
}

// Función para eliminar una cancha
function deleteCancha(id) {
    if (confirm('¿Estás seguro de que deseas eliminar esta cancha?')) {
        $.ajax({
            url: `${apiUrl}/${id}`,
            method: 'DELETE',
            success: function(response) {
                alert("Cancha eliminada exitosamente!");
                loadCanchas();  // Recargar las canchas después de la eliminación
            },
            error: function(xhr, status, error) {
                alert("Error al eliminar la cancha: " + error);
            }
        });
    }
}

// Función para limpiar el formulario
function resetForm() {
    $('#crudForm')[0].reset();
    $('#id').val('');
}

// Cargar las canchas cuando la página esté lista
$(document).ready(function() {
    loadCanchas();
});
