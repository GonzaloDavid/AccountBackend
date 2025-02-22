package com.accountmasivebackend.dao;

import com.accountmasivebackend.entities.LoadFile;
import jakarta.ejb.Stateless;

import java.util.Date;

@Stateless
public class LoadfileDAO extends GenericDAO<LoadFile> {
    public LoadfileDAO(){
        super(LoadFile.class);
    }

    public LoadFile createLoadfileObject(String namefile)
    {
        LoadFile loadFile=new LoadFile();
        loadFile.setSeqLoadFile(0l);
        loadFile.setNameLoadFile(namefile);
        loadFile.setDateProcess(new Date());
        loadFile.setStatus("PROCESADO");
        loadFile.setDateCreate(new Date());
        loadFile.setDateLastModify(new Date());
        loadFile.setUserCreate(0l);
        loadFile.setUserLasModify(0l);

        insert(loadFile);
        flush();
        return loadFile;

    }
}