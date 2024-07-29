$(document).ready(function(){
	// Activate tooltip
	$('[data-toggle="tooltip"]').tooltip();
	
	// Select/Deselect checkboxes
	var checkbox = $('table tbody input[type="checkbox"]');
	$("#selectAll").click(function(){
		if(this.checked){
			checkbox.each(function(){
				this.checked = true;                        
			});
		} else{
			checkbox.each(function(){
				this.checked = false;                        
			});
		} 
	});
	checkbox.click(function(){
		if(!this.checked){
			$("#selectAll").prop("checked", false);
		}
	});
});

document.addEventListener('DOMContentLoaded', function () {
    const editButtons = document.querySelectorAll('[data-bs-target="#editEmployeeModal"]');
    editButtons.forEach(button => {
        button.addEventListener('click', function () {
            const employeeId = this.getAttribute('data-employee-id');
            // Aquí puedes hacer una llamada AJAX para obtener los detalles del empleado si es necesario
            // Luego, llenar el modal con esos detalles
        });
    });
});
