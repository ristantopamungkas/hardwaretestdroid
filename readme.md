###step git:

sebelum memulai bekerja, tiap harinya lakukan pengecekan versioning command:
git checkout master && git fetch && git merge origin/master

silahkan lakukan perubahan2 sesuai kebutuhan development

bila telah selesai melakukan perubahan, jalankan perintah seperti di nomor 1 kembali

buat branch baru agar brach development memiliki version history apabila terdapat conflict dapat diatasi dengan mudah command:
git stash && git checkout -b namabranchbaru development && git stash pop

tambahkan hasil perubahan ke dalam repositori git local anda command: untuk menambahkan semua perubahan gunakan perintah ini: git add . (titik menandakan untuk menambahkan semua perubahan kedalam repositori lokal anda)

untuk menambahkan perubahan satu-persatu gunakan perintah ini:
git add folder/folder/namafile

commit semua perubahan yang telah anda tambahkan ke dalam repositori lokal command:
git commit -m "pesan perubahan"

dorong perubahan di lokal anda ke dalam repository github command:
git push -u origin namabranchbaru

lakukan pull request di
https://github.com/aronjahja/ekc.git
agar dapat segera dilakukan merge ke dalam branch development

setelah di merge oleh reviewer di github, maka update perubahan pada branch development di lokal anda seperti comman nomor 1 command:
git stash && git checkout development && git fetch && git merge origin/development && git stash pop

###catatan: mohon di ikuti langkah2 diatas agar tidak terjadi kerancuan
