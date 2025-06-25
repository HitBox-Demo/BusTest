/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


// Use event-delegation to catch all .seat clicks in one listener
document.addEventListener("DOMContentLoaded", () => {
    const container = document.querySelector(".seat-map");
    const hidden = document.getElementById("selectedSeats");
    if (!container || !hidden) {
        console.error("Seat-selection elements missing");
        return;
    }

    container.addEventListener("click", (e) => {
        const btn = e.target;
        // Only toggle on real seat buttons that aren't already booked
        if (!btn.classList.contains("seat") || btn.classList.contains("booked")) {
            return;
        }

        // Toggle visual state
        btn.classList.toggle("selected");

        // Rebuild hidden field as comma-list of all currently selected seats
        const selected = Array.from(
                container.querySelectorAll(".seat.selected")
                ).map(b => b.dataset.seat);

        hidden.value = selected.join(",");
        console.log("Seat-selection updated:", hidden.value);
    });
});
