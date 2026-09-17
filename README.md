<img width="581" height="505" alt="image" src="https://github.com/user-attachments/assets/25134cf1-da83-4e15-b341-7fb5c7e48c56" /># Mini_Project

Make the branches name according to you names and start working upon project
Also make sure once the code from your side is completed please upload it on the *main branch
Update the README accordingly also

THIS IS HOW JAVAFX WORKS:

Background: How a JavaFX Application is Structured

STAGE is the actual application window — the frame with a title bar, close button, and border that the operating system displays. Every JavaFX application has one main Stage, created automatically when the application starts.

SCENE is the container for everything visible inside that window. A Stage can only display one Scene at a time (though it can be swapped out, e.g. switching from a "menu screen" to an "editor screen"). The Scene holds the actual content — buttons, canvases, toolbars — and defines things like the window's width and height.

NODE is the general term for anything that can be placed inside a Scene — a button, a shape, an image, a text label, or *a container that groups other nodes together*. Every visible element in a JavaFX application, no matter how simple or complex, is a Node.

THE SCENE GRAPH is the tree structure formed by nesting nodes inside other nodes. For example, a Scene might contain a BorderPane (a layout container), which contains a Toollbar at the top and a drawing area in the center; that drawing area might contain individual shapes representing each logic gate. This entire nested structure — from the Scene down to the smallest shape — is called the scene graph, and JavaFX automatically handles drawing it, detecting mouse clicks on the correct node, and redrawing it when something changes.

<img width="581" height="505" alt="Screenshot from 2026-09-17 13-11-04" src="https://github.com/user-attachments/assets/198c95e6-a240-4646-8bc9-f36092a1f0e2" />


Why this matters for the project: every interactive feature already implemented — clicking to place a gate, clicking a port to draw a wire, dragging a component, toggling a switch — works because these elements are all genuine Node objects living inside the same scene graph. JavaFX's built-in mouse-event system (onMouseClicked, onMouseDragged, etc.) automatically knows which node was clicked, based on its position in this tree, and the project's Circuit/Port/Wire classes are simply attached to these nodes to track the underlying logic.

This is the reason the choice between the two SVG rendering approaches matters so much: one approach (fxsvgimage) produces genuine nodes that slot into this same scene graph, keeping the entire application's interaction logic consistent in one place. The other (WebView) renders content inside an embedded web browser, which is technically also a Node in the scene graph, but everything drawn inside it is invisible to JavaFX's own node system — it exists in a separate browser document with its own internal structure that JavaFX cannot see into or directly interact with. Any click, drag, or selection happening inside a WebView needs a completely separate piece of code (written in JavaScript, not Java) to detect and then relay back to the rest of the application.

We were having conflict on which idea to be used for reading -> parsing -> selection(drag and drop) of svg.
JavaFX has no built-in SVG image loader (unlike PNG/JPEG). SVG files contain vector elements (<path>, <circle>, <rect>, <g>) that JavaFX's Image class cannot interpret directly — parsing is required. Two viable approaches were evaluated for rendering gate, switch, and LED graphics from SVG source files.
2 ideas were finalized
1. fxsvgimage - SVG to Node Parsing
This library parses SVG markup and converts each element into a native JavaFX Shape node (SVGPath, Circle, Rectangle, etc.), grouped into a javafx.scene.Group. The resulting group is inserted directly into the application's existing scene graph, alongside wires, ports, and selection overlays.
