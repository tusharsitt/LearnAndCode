TYPE: REDUNDANT
- "// This method processes an order" (Method name is ProcessOrder)
- "// Check if order is null" (Code is: if (order == null))
- "// Validate the order" (Code is: IsValidOrder(order))
- "// Check inventory"
- "// Reserve inventory"
- "// Process payment"
- "// If no inventory, return failure"
- "// Return success"
- "// Throw it"

TYPE: JOURNALING
- "// Added by John on 12/15/2023 - needed for the new feature"

TYPE: ATTRIBUTION
- "// John says we need to refund here"

TYPE: NOISE 
- "// This is important!!!"
- "// Something went wrong"
- "// Implementation here" (inside the empty methods)

TYPE: VAGUE TODO
- "// TODO: Fix this later"


### Reflection

- Working on this assignment really highlighted how "over-commenting" can clutter code instead of helping it. I realized that most of the comments in the provided snippet were just noise because the code itself was already clear. I also noticed the original code violated the Single Responsibility Principle, so I refactored it to separate the logic properly. In the end, removing the unnecessary comments and fixing the structure made the code much easier to read and maintain.
