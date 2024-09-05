describe('ConfirmModal Component', () => {
    beforeEach(() => {
      // Visita la página que contiene el componente ConfirmModal
      cy.visit('/ruta-a-tu-componente');
    });
  
    it('should display the modal with correct title and message', () => {
      // Abre el modal
      cy.get('button').contains('Eliminar').click();
  
      // Verifica que el modal esté visible
      cy.get('.modal-overlay').should('be.visible');
  
      // Verifica el título y el mensaje del modal
      cy.get('.modal-header h3').should('contain', 'Confirmar Eliminación');
      cy.get('.modal-body p').should('contain', '¿Estás seguro de que deseas eliminar este cromo?');
    });
  
    it('should close the modal when cancel button is clicked', () => {
      // Abre el modal
      cy.get('button').contains('Eliminar').click();
  
      // Haz clic en el botón Cancelar
      cy.get('button').contains('Cancelar').click();
  
      // Verifica que el modal ya no esté visible
      cy.get('.modal-overlay').should('not.exist');
    });
  
    it('should emit events and close the modal when confirm button is clicked', () => {
      // Intercepta la solicitud de eliminación
      cy.intercept('DELETE', '/api/cromos/*', {
        statusCode: 200,
        body: {}
      }).as('deleteCromo');
  
      // Abre el modal
      cy.get('button').contains('Eliminar').click();
  
      // Haz clic en el botón Confirmar
      cy.get('button').contains('Confirmar').click();
  
      // Verifica que la solicitud de eliminación se haya realizado
      cy.wait('@deleteCromo').its('response.statusCode').should('eq', 200);
  
      // Verifica que el modal ya no esté visible
      cy.get('.modal-overlay').should('not.exist');
  
      // Verifica que se haya emitido el evento para actualizar la lista de cromos
      // Aquí puedes agregar más verificaciones según sea necesario
    });
  });