<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Évaluation de l'employé</title>
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            background-color: #FDF9F2;
            padding: 30px;
            color: #2C1810;
        }

        h2, h3 {
            color: #7A051F;
        }

        .mois-container {
            display: flex;
            flex-wrap: wrap;
            gap: 12px;
            margin-top: 20px;
        }

        .mois-btn {
            padding: 10px 18px;
            background: #7A051F;
            color: white;
            border: none;
            border-radius: 8px;
            cursor: pointer;
            font-weight: 500;
            transition: background 0.3s;
        }

        .mois-btn:hover {
            background: #8A1A2F;
        }

        #zone-evaluation {
            margin-top: 30px;
            padding: 20px;
            background: white;
            border-radius: 12px;
            box-shadow: 0 4px 20px rgba(122, 5, 31, 0.1);
            border-left: 6px solid #7A051F;
        }

        .etoiles {
            margin-top: 10px;
        }

        .etoiles .star {
            font-size: 2.2rem;
            cursor: pointer;
            color: #ccc;
            transition: color 0.3s;
        }

        .etoiles .star:hover {
            color: #FFD700;
        }

        .note-label {
            margin-top: 10px;
            font-weight: 500;
        }

        .btn-submit {
            margin-top: 15px;
            padding: 10px 20px;
            background: #7A051F;
            color: white;
            border: none;
            border-radius: 6px;
            cursor: pointer;
        }

        .btn-submit:hover {
            background: #8A1A2F;
        }
        .btn-retour {
    display: inline-block;
    padding: 10px 18px;
    background: #ccc;
    color: #2C1810;
    text-decoration: none;
    border-radius: 6px;
    font-weight: 500;
    transition: background 0.3s;
}

.btn-retour:hover {
    background: #bbb;
}

    </style>
</head>
<body>

    <h2>Évaluation de ${employe.nom} ${employe.prenom}</h2>
    <p>Poste : <strong>${employe.poste}</strong></p>
    <p>Département : <strong>${employe.departement}</strong></p>

    <h3>Choisissez un mois :</h3>
    <div class="mois-container">
        <c:forEach var="mois" items="${['Janvier','Février','Mars','Avril','Mai','Juin','Juillet','Août','Septembre','Octobre','Novembre','Décembre']}">
            <button type="button" class="mois-btn" onclick="afficherEvaluation('${mois}')">${mois}</button>
        </c:forEach>
    </div>

    <div id="zone-evaluation" style="display:none;">
        <h3 id="mois-selectionne"></h3>
        <p class="note-label">Attribuez une note :</p>
        <div class="etoiles">
            <span class="star" onclick="noter(1)">★</span>
            <span class="star" onclick="noter(2)">★</span>
            <span class="star" onclick="noter(3)">★</span>
            <span class="star" onclick="noter(4)">★</span>
            <span class="star" onclick="noter(5)">★</span>
        </div>

        <!-- FORMULAIRE POUR ENREGISTRER -->
        <form action="${pageContext.request.contextPath}/rh/evaluation/save" method="post">
            <input type="hidden" id="note" name="note" value="0">
            <input type="hidden" id="moisHidden" name="mois" value="">
            <input type="hidden" name="id_employe" value="${employe.id}">
            <button type="submit" class="btn-submit">Enregistrer</button>
        </form>
    </div>

    <script>
        function afficherEvaluation(mois) {
            document.getElementById("zone-evaluation").style.display = "block";
            document.getElementById("mois-selectionne").innerText = "Évaluation pour " + mois;
            document.getElementById("moisHidden").value = mois;
            noter(0); // reset étoiles
        }

        function noter(valeur) {
            document.getElementById("note").value = valeur;
            const etoiles = document.querySelectorAll(".star");
            etoiles.forEach((e, i) => {
                e.style.color = i < valeur ? "#FFD700" : "#ccc";
            });
        }
    </script>
<!-- Bouton retour -->
<div style="margin-top: 25px;">
    <a href="${pageContext.request.contextPath}/rh/scoring" 
       class="btn-retour">← Retour au RH</a>
</div>

</body>
</html>
