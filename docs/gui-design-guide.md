# GUI Design Guide for MotorPH Payroll System

## Setting up NetBeans for Visual GUI Design

### 1. Install NetBeans IDE
- Download NetBeans IDE from: https://netbeans.apache.org/
- Choose the "Java SE" or "All" bundle
- Install with JDK support

### 2. Import Your Project
1. Open NetBeans
2. File → Open Project
3. Navigate to your project folder
4. Select the project (NetBeans will recognize it as a Maven project)

### 3. Create GUI Forms with Visual Designer

#### Creating a New JFrame Form:
1. Right-click on your package (e.g., `com.motorph.view`)
2. New → JFrame Form
3. Enter class name (e.g., `LoginFormDesigner`)
4. Click Finish

#### Converting Existing Forms:
Since your current forms are hand-coded, you have two options:

**Option A: Recreate using Visual Designer**
1. Create new JFrame Form
2. Use drag-and-drop to recreate the layout
3. Copy business logic from existing forms

**Option B: Convert Existing Forms**
1. Rename your current `.java` file to `.java.backup`
2. Create new JFrame Form with same name
3. Recreate layout visually
4. Copy event handlers and business logic

### 4. Using the Visual Designer

#### Drag-and-Drop Components:
1. **Palette Window**: Contains all Swing components
   - Swing Controls: JButton, JTextField, JLabel, etc.
   - Swing Containers: JPanel, JScrollPane, etc.
   - Swing Menus: JMenuBar, JMenu, etc.

2. **Design View**: Visual representation of your form
   - Drag components from Palette to Design view
   - Resize and position components visually
   - Set alignment guides and snap-to-grid

3. **Properties Window**: Edit component properties
   - Text, Font, Color, Size, etc.
   - Layout constraints
   - Event handlers

#### Layout Managers:
- **Absolute Layout**: Precise pixel positioning (like your current forms)
- **BorderLayout**: North, South, East, West, Center regions
- **GridBagLayout**: Flexible grid-based layout
- **GroupLayout**: NetBeans' preferred layout manager

### 5. Example: Converting LoginForm to Visual Design

#### Step 1: Create New JFrame Form
```
File → New → JFrame Form
Name: LoginFormVisual
Package: com.motorph.view
```

#### Step 2: Design the Layout
1. **Set Form Properties**:
   - Title: "MOTOR PH PAYROLL SYSTEM"
   - Size: 960 x 502
   - Resizable: false

2. **Add Background**:
   - Drag JLabel to form
   - Set icon property to background image
   - Position and size to cover entire form

3. **Add Login Panel**:
   - Drag JPanel to form
   - Set background color and border
   - Position over background image

4. **Add Components to Panel**:
   - Drag JLabel for "Welcome" text
   - Drag JTextField for username
   - Drag JPasswordField for password
   - Drag JButton for login
   - Set properties for each component

#### Step 3: Add Event Handlers
1. Right-click on Login button
2. Events → Action → actionPerformed
3. NetBeans creates event handler method
4. Copy your existing login logic

#### Step 4: Generated Code Structure
```java
public class LoginFormVisual extends JFrame {
    // NetBeans generates component declarations
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    
    public LoginFormVisual() {
        initComponents(); // NetBeans generated
        // Your custom initialization code here
    }
    
    // NetBeans generated GUI code (protected)
    private void initComponents() {
        // Auto-generated layout code
    }
    
    // Your event handlers
    private void btnLoginActionPerformed(ActionEvent evt) {
        // Your login logic here
    }
}
```

### 6. Best Practices for GUI Design

#### Layout Tips:
1. **Use GroupLayout** for complex forms (NetBeans default)
2. **Use BorderLayout** for simple layouts with regions
3. **Use GridBagLayout** for forms with many input fields
4. **Avoid AbsoluteLayout** except for precise positioning needs

#### Component Organization:
1. **Group related components** in JPanels
2. **Use consistent spacing** between components
3. **Align components** properly using layout guides
4. **Set proper tab order** for keyboard navigation

#### Responsive Design:
1. **Use layout managers** instead of fixed positioning
2. **Set minimum/maximum sizes** for components
3. **Test with different screen sizes**
4. **Use relative sizing** where possible

### 7. Alternative Tools

#### IntelliJ IDEA Ultimate:
- Has GUI Designer plugin
- Similar drag-and-drop functionality
- Good integration with Maven projects

#### Eclipse WindowBuilder:
- Free plugin for Eclipse IDE
- Visual GUI designer for Swing
- Install from Eclipse Marketplace

#### Standalone Tools:
- **JFormDesigner**: Commercial Swing designer
- **NetBeans Platform**: For complex applications

### 8. Converting Your Current Forms

Here's a step-by-step approach to convert your existing forms:

#### For LoginForm:
1. Create `LoginFormDesigner.java` using NetBeans visual designer
2. Recreate the layout visually
3. Copy the `LoginController` integration
4. Copy event handling logic
5. Test thoroughly
6. Replace old form with new one

#### For Dashboard Forms:
1. Use **CardLayout** for switching between panels
2. Create separate panels for different sections
3. Use **JTable** components for data display
4. Implement proper event handling for CRUD operations

### 9. Sample Visual Design Workflow

```java
// 1. Create form visually in NetBeans
// 2. NetBeans generates this structure:

public class EmployeeDashboardVisual extends JFrame {
    // Auto-generated component declarations
    private JPanel sidebarPanel;
    private JPanel mainPanel;
    private JButton btnEmployeeDetails;
    private JButton btnLeaveRequest;
    private JTable employeeTable;
    
    public EmployeeDashboardVisual() {
        initComponents(); // NetBeans generated
        setupServices();  // Your custom setup
        loadData();       // Your data loading
    }
    
    // NetBeans protects this method
    @SuppressWarnings("unchecked")
    private void initComponents() {
        // Auto-generated layout code
        // DO NOT MODIFY THIS METHOD
    }
    
    // Your custom methods
    private void setupServices() {
        this.employeeService = new EmployeeService();
        // ... other service initialization
    }
    
    // Event handlers (created by NetBeans when you add events)
    private void btnEmployeeDetailsActionPerformed(ActionEvent evt) {
        // Your business logic here
    }
}
```

### 10. Tips for Success

1. **Start Simple**: Begin with basic layouts and add complexity gradually
2. **Use Panels**: Group related components in JPanels for better organization
3. **Test Frequently**: Run your application often to see how it looks
4. **Learn Layout Managers**: Understanding layouts is key to good GUI design
5. **Keep Business Logic Separate**: Don't mix GUI code with business logic
6. **Use MVC Pattern**: Maintain separation between view and controller

This approach will give you the drag-and-drop GUI editing capability you're looking for while maintaining the clean architecture of your refactored system.