import os
from PIL import Image


def double_image_size(image_path):
    # Ouvre l'image
    original_image = Image.open(image_path)
    # Obtient les dimensions de l'image
    width, height = original_image.size

    # Crée une nouvelle image avec une taille deux fois plus grande que l'originale

    new_image = original_image.resize((width * 2, height * 2), Image.Resampling.NEAREST)

    return new_image


# Dossier d'entrée contenant les images à traiter
input_folder = "input"

# Dossier de sortie pour les images traitées
output_folder = "output"
os.makedirs(output_folder, exist_ok=True)

# Parcourt toutes les images dans le dossier d'entrée
for filename in os.listdir(input_folder):
    if filename.endswith(".jpg") or filename.endswith(".png"):  # Vérifie les extensions d'image
        # Construit le chemin complet de l'image d'entrée
        input_image_path = os.path.join(input_folder, filename)

        # Double la taille de l'image
        new_image = double_image_size(input_image_path)

        # Construit le chemin complet de l'image de sortie
        output_image_path = os.path.join(output_folder, filename)

        # Enregistre la nouvelle image
        new_image.save(output_image_path)
        print("L'image avec les pixels doublés a été enregistrée sous :", output_image_path)
