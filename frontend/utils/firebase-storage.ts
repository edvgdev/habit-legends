
import { getStorage, ref, uploadBytes, getDownloadURL, deleteObject } from "firebase/storage";
import app from '@/services/firebase-config';

/**
 * Uploads a file to Firebase Storage and returns its download URL.
 * @param {File} file - The file to upload.
 * @param {string} fileName - The name to save the file as.
 * @returns {Promise<string>} - The download URL of the uploaded file.
 */
export const uploadPhotoToFirebase = async (file: File, fileName: string, destination: string) => {
    if (!file) throw new Error("No file provided for upload.");

    const storage = getStorage(app);
    const fileRef = ref(storage, `${destination}/${fileName}-${Date.now()}`); // Generate unique file name

    try {
        await uploadBytes(fileRef, file); // Upload the file
        const fileUrl = await getDownloadURL(fileRef); // Get the download URL
        console.log("File uploaded successfully. URL:", fileUrl);
        return fileUrl;
    } catch (error) {
        console.error("Error uploading file to Firebase:", error);
        throw error;
    }
};

/**
* Deletes a file from Firebase Storage.
* @param {string} photoPath - The full path of the file in Firebase Storage.
*/
export const deletePhoto = async (photoPath: string) => {
    if (!photoPath) throw new Error("No file path provided for deletion.");

    const storage = getStorage(app);
    const fileRef = ref(storage, photoPath);

    try {
        await deleteObject(fileRef);
        console.log("File deleted successfully.");
    } catch (error) {
        console.error("Error deleting file:", error);
        throw error;
    }
};