import nltk
import sys
nltk.download('punkt')
from nltk.stem.lancaster import LancasterStemmer
stemmer = LancasterStemmer()
import tkinter
from tkinter import *
import time
import numpy
import tflearn
import tensorflow
import random
import json
import os

cwd = os.getcwd()
print(cwd)

with open("Help/chatBot/intents.json") as file:
    data = json.load(file)

words = []
labels = []
docs_x = []
docs_y = []

for intent in data["intents"]:
    for pattern in intent["patterns"]:
        wrds = nltk.word_tokenize(pattern)
        words.extend(wrds)
        docs_x.append(wrds)
        docs_y.append(intent["tag"])

    if intent["tag"] not in labels:
        labels.append(intent["tag"])

words = [stemmer.stem(w.lower()) for w in words if w != "?"]
words = sorted(list(set(words)))

labels = sorted(labels)

training = []
output = []

out_empty = [0 for _ in range(len(labels))]

for x, doc in enumerate(docs_x):
    bag = []

    wrds = [stemmer.stem(w.lower()) for w in doc]

    for w in words:
        if w in wrds:
            bag.append(1)
        else:
            bag.append(0)

    output_row = out_empty[:]
    output_row[labels.index(docs_y[x])] = 1

    training.append(bag)
    output.append(output_row)


#Train Neural Network

training = numpy.array(training)
output = numpy.array(output)

tensorflow.reset_default_graph()

net = tflearn.input_data(shape=[None, len(training[0])])
net = tflearn.fully_connected(net, 8)
net = tflearn.fully_connected(net, 8)
net = tflearn.fully_connected(net, len(output[0]), activation="softmax")
net = tflearn.regression(net)

model = tflearn.DNN(net)
model.fit(training, output, n_epoch=1000, batch_size=8, show_metric=True)
filePath = cwd+"/model.tflearn"
model.save(filePath)

def bag_of_words(s, words):
    bag = [0 for _ in range(len(words))]

    s_words = nltk.word_tokenize(s)
    s_words = [stemmer.stem(word.lower()) for word in s_words]

    for se in s_words:
        for i, w in enumerate(words):
            if w == se:
                bag[i] = 1

    return numpy.array(bag)


#Button Send
def send():
    msg = EntryBox.get("1.0",'end-1c').strip()
    EntryBox.delete("0.0",END)

    if(("q" in msg.lower()) or (msg.lower() == "quit")):
         base.destroy()

    ChatLog.config(state=NORMAL)
    ChatLog.insert(END, "You: " + msg + '\n\n')
    ChatLog.config(foreground="#442265", font=("Verdana", 12 ))
    results = model.predict([bag_of_words(msg, words)])
    results_index = numpy.argmax(results)
    tag = labels[results_index]
    for tg in data["intents"]:
        if tg['tag'] == tag:
            responses = tg['responses']

    response = random.choice(responses)
    print(response)
#     time.sleep(3)
    ChatLog.insert(END, "Bot: " + response + '\n\n')
    ChatLog.config(state=DISABLED)
    ChatLog.yview(END)
    if(msg.lower() == "bye"):
        base.destroy()


#On Click
def onClick(event):
    msg = EntryBox.get("1.0",'end-1c').strip()
    EntryBox.delete("0.0",END)
    if(("q" in msg.lower()) or (msg.lower() == "quit")):
         base.destroy()
    else:
         ChatLog.config(state=NORMAL)
         ChatLog.insert(END, "You: " + msg + '\n\n')
         ChatLog.config(foreground="#442265", font=("Verdana", 12 ))
         results = model.predict([bag_of_words(msg, words)])
         results_index = numpy.argmax(results)
         tag = labels[results_index]
         for tg in data["intents"]:
            if tg['tag'] == tag:
               responses = tg['responses']

         response = random.choice(responses)
         print(response)
#        time.sleep(3)
         ChatLog.insert(END, "Bot: " + response + '\n\n')
         ChatLog.config(state=DISABLED)
         ChatLog.yview(END)
         if(msg.lower() == "bye"):
             base.destroy()


base = Tk()
base.title("Chat")
base.geometry("400x520")
base.resizable(width=FALSE, height=FALSE)

#Create Chat window
ChatLog = Text(base, bd=0, bg="white", height="8", width="60", font="Arial")

#ChatLog.config(state=DISABLED)
ChatLog.config(state=NORMAL)
ChatLog.insert(END, "Bot: " + "Welcome to Bot! Enter quit or q to terminate." + '\n\n')

#Bind scrollbar to Chat window
scrollbar = Scrollbar(base, command=ChatLog.yview, cursor="heart")
ChatLog['yscrollcommand'] = scrollbar.set

#Create Button to send message
SendButton = Button(base, font=("Verdana",12,'bold'), text="Send", width="12", height=5,
                    bd=0, bg="#32de97", activebackground="#3c9d9b",fg='#ffffff',
                    command= send )

#Create the box to enter message
EntryBox = Text(base, bd=0, bg="white",width="29", height="5", font="Arial")
EntryBox.bind("<Return>", onClick)

#Place all components on the screen
scrollbar.place(x=376,y=6, height=386)
ChatLog.place(x=6,y=6, height=386, width=370)
EntryBox.place(x=128, y=401, height=90, width=265)
SendButton.place(x=6, y=401, height=90)
base.mainloop()