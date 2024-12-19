package com.irza.greenbox.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.irza.greenbox.model.post.PostModel
import com.irza.greenbox.model.reward.RewardModel
import com.irza.greenbox.model.user.UserModel

class Repository {
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    fun signUp(
        email : String,
        password : String,
        nama: String,
        onSuccess: () -> Unit,
        onFailed: (Exception) -> Unit
    ){
        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                firestore
                    .collection("user")
                    .document(it.user?.uid ?: "")
                    .set(
                        UserModel(
                            uid = it.user?.uid ?: "",
                            nama = nama,
                            level = 1,
                            point = 0,
                        )
                    )
                    .addOnSuccessListener {
                        onSuccess()
                    }
                    .addOnFailureListener {
                        onFailed(it)
                    }
            }
            .addOnFailureListener{
                onFailed(it)
            }
    }

    fun login(
        email: String,
        password: String,
        onSuccess: (Boolean) -> Unit,
        onFailed: (Exception) -> Unit
    ){
        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                onSuccess(true)
            }
            .addOnFailureListener {
                onFailed(it)
            }
    }

    fun logout(onSuccess: () -> Unit, onFailed: (Exception) -> Unit) {
        try {
            auth.signOut()
            onSuccess()
        } catch (exception: Exception) {
            onFailed(exception)
        }
    }

    fun getUser(
        uid: String,
        onSuccess: (UserModel) -> Unit,
        onFailed: (Exception) -> Unit,
    ){
        firestore
            .collection("user")
            .document(uid)
            .addSnapshotListener { value, error ->
                if (error != null) {
                    onFailed(error)
                }
                value?.let { doc ->
                    onSuccess(
                        UserModel(
                            uid = auth.uid ?: "",
                            nama = doc["nama"] as String,
                            level = doc["level"] as Long,
                            point = doc["point"] as Long,
                        )
                    )
                    return@addSnapshotListener
                }
            }
    }

    fun getAllUserByPoint(
        onSuccess: (List<UserModel>) -> Unit,
        onFailed: (Exception) -> Unit
    ) {
        firestore
            .collection("user")
            .orderBy("point", Query.Direction.DESCENDING)
            .get()
            .addOnSuccessListener { querySnapshot ->
                val userList = querySnapshot.documents.map { doc ->
                    UserModel(
                        uid = doc.getString("uid") ?: "",
                        nama = doc.getString("nama") ?: "",
                        level = doc.getLong("level") ?: 0,
                        point = doc.getLong("point") ?: 0
                    )
                }
                onSuccess(userList)
            }
            .addOnFailureListener { exception ->
                onFailed(exception)
            }
    }


    fun getAllPost(
        onSuccess: (List<PostModel>) -> Unit,
        onFailed: (Exception) -> Unit
    ) {
        firestore
            .collection("post")
            .addSnapshotListener { value, error ->
                if (error != null) {
                    onFailed(error)
                    return@addSnapshotListener
                }
                value?.let {
                    onSuccess(
                        it.documents.map { doc ->
                            PostModel(
                                id = doc?.getString("id") ?: "",
                                judul = doc?.getString("judul") ?: "",
                                kategori = doc?.getString("kategori") ?: "",
                                isi = doc?.getString("isi") ?: ""
                            )
                        }
                    )
                    return@addSnapshotListener
                }
            }
    }

    fun getPostById(
        id: String,
        onSuccess: (PostModel) -> Unit,
        onFailed: (Exception) -> Unit
    ) {
        firestore
            .collection("post")
            .document(id)
            .addSnapshotListener { value, error ->
                if (error != null) {
                    onFailed(error)
                }
                value?.let { doc ->
                    onSuccess(
                        PostModel(
                            id = doc["id"] as String,
                            judul = doc["judul"] as String,
                            kategori = doc["kategori"] as String,
                            isi = doc["isi"] as String
                        )
                    )
                    return@addSnapshotListener
                }
            }
    }

    fun getAllReward(
        onSuccess: (List<RewardModel>) -> Unit,
        onFailed: (Exception) -> Unit
    ) {
        firestore
            .collection("reward")
            .addSnapshotListener { value, error ->
                if (error != null) {
                    onFailed(error)
                    return@addSnapshotListener
                }
                value?.let {
                    onSuccess(
                        it.documents.map { doc ->
                            RewardModel(
                                id = doc?.getString("id") ?: "",
                                point = doc?.getLong("point") ?: 0,
                                deskripsi = doc?.getString("deskripsi") ?: "",
                                nama = doc?.getString("nama") ?: ""
                            )
                        }
                    )
                    return@addSnapshotListener
                }
            }
    }

    fun getRewardById(
        id: String,
        onSuccess: (RewardModel) -> Unit,
        onFailed: (Exception) -> Unit
    ) {
        firestore
            .collection("reward")
            .document(id)
            .addSnapshotListener { value, error ->
                if (error != null) {
                    onFailed(error)
                }
                value?.let { doc ->
                    onSuccess(
                        RewardModel(
                            id = doc["id"] as String,
                            point = doc["point"] as Long,
                            deskripsi = doc["deskripsi"] as String,
                            nama = doc["nama"] as String
                        )
                    )
                    return@addSnapshotListener
                }
            }
    }
}