package com.example.kanji_n5;

public class Kanji {
    private String audio;
    private String kanji;
    private String meaning;
    private String pdf;
    private String usage;
    private String video;
    private String word;

    public Kanji(){

    }
    public Kanji(String audio,String kanji,String meaning,String pdf,String usage,String video,String word){
        this.audio = audio;
        this.kanji = kanji;
        this.meaning = meaning;
        this.pdf = pdf;
        this.usage = usage;
        this.video = video;
        this.word = word;
    }

    public String getAudio(){return audio;}
    public String getKanji(){return kanji;}
    public String getMeaning(){return meaning;}
    public String getPdf(){return pdf;}
    public String getUsage(){return usage;}
    public String getVideo(){return video;}
    public String getWord(){return word;}



}
