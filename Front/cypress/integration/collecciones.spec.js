describe('Collecciones Page', () => {
    it('should display the list of albums', () => {
      cy.visit('/collecciones');
      cy.contains('h1', 'Colecciones');
      cy.get('.card').should('have.length.greaterThan', 0);
    });
  
    it('should navigate to Cromos page when "Ver Más" button is clicked', () => {
      cy.visit('/collecciones');
      cy.get('.btn-primary').first().click();
      cy.url().should('include', '/cromos');
    });
  });