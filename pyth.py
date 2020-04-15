import sys
from pytube import YouTube,Stream,Playlist
import requests
import re
import os
from pytube.monostate import Monostate

def downloadYouTube(videourl,downloadpath):
    yt = YouTube(videourl,on_progress_callback=progress_function)
    print(yt.title)
    print(yt.thumbnail_url)
    yt = yt.streams.filter(progressive=True,file_extension='mp4').order_by('resolution').desc().first()
    if not os.path.exists(downloadpath):
        os.makedirs(downloadpath)
    yt.download(downloadpath)
    print(yt.is_progressive)

def progress_function(chunk,file_handle, bytes_remaining):
    print(bytes_remaining)
    size = 10000
    p = 0
    while p <= 100:
        progress = p
        p = percent(bytes_remaining, size)

def percent(tem, total):
        perc = (float(tem) / float(total)) * float(100)
        return perc

if __name__ == "__main__":
   downloadYouTube(sys.argv[1],sys.argv[2])
