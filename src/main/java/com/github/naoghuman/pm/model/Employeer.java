/*
 * Copyright (C) 2018 Naoghuman
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.github.naoghuman.pm.model;

import com.github.naoghuman.pm.configuration.DefaultConfiguration;
import com.github.naoghuman.pm.configuration.EmployeerConfiguration;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 *
 * @author Naoghuman
 * @since  0.1.0
 */
@Entity
@Access(AccessType.PROPERTY)
@Table(name = EmployeerConfiguration.ENTITY__TABLE_NAME__EMPLOYEER)
@NamedQueries({
    @NamedQuery(
            name  = EmployeerConfiguration.NAMED_QUERY__NAME__FIND_ALL_ARE_FAVORITE,
            query = EmployeerConfiguration.NAMED_QUERY__QUERY__FIND_ALL_ARE_FAVORITE),
    @NamedQuery(
            name  = EmployeerConfiguration.NAMED_QUERY__NAME__FIND_ALL_NOT_FAVORITE,
            query = EmployeerConfiguration.NAMED_QUERY__QUERY__FIND_ALL_NOT_FAVORITE)
})
public class Employeer implements 
        Comparable<Employeer>, Externalizable, 
        DefaultConfiguration, EmployeerConfiguration
{
    public Employeer() {
        this(DEFAULT_ID,              DEFAULT_ID,              // id,               generationTime
                DEFAULT_STRING_EMPTY,          DEFAULT_STRING_EMPTY,          // firstName,        secondName
                DEFAULT_STRING_EMPTY,          new ArrayList<String>(), // lastName,         titles
                DEFAULT_INDEX,           DEFAULT_BOOLEAN,         // index,            favorite
                new ArrayList<String>(), new ArrayList<String>(), // responsibilities, phones
                new ArrayList<String>(), DEFAULT_STRING_EMPTY,          // emails,           office
                DEFAULT_STRING_EMPTY,          new ArrayList<Long>(),   // icon,             tagIds
                new ArrayList<Long>());                           // linkIds
    }

    public Employeer(
            final long id,                            final long generationTime,
            final String firstName,                   final String secondName,
            final String lastName,                    final ArrayList<String> titles,
            final int index,                          final boolean favorite,
            final ArrayList<String> responsibilities, final ArrayList<String> phones,
            final ArrayList<String> emails,           final String office,
            final String icon,                        final ArrayList<Long> tagIds,
            final ArrayList<Long> linkIds
    ) {
        this.init(
                id,               generationTime,
                firstName,        secondName,
                lastName,         titles,
                index,            favorite,
                responsibilities, phones,
                emails,           office,
                icon,             tagIds,
                linkIds);
    }
    
    private void init(
            final long id,                            final long generationTime,
            final String firstName,                   final String secondName,
            final String lastName,                    final ArrayList<String> titles,
            final int index,                          final boolean favorite,
            final ArrayList<String> responsibilities, final ArrayList<String> phones,
            final ArrayList<String> emails,           final String office,
            final String icon,                        final ArrayList<Long> tagIds,
            final ArrayList<Long> linkIds
    ) {
        this.setId(id);
        this.setGenerationTime(generationTime);
        this.setFirstName(firstName);
        this.setSecondName(secondName);
        this.setLastName(lastName);
        this.setTitles(titles);
        this.setIndex(index);
        this.setFavorite(favorite);
        this.setResponsibilities(responsibilities);
        this.setPhones(phones);
        this.setEMails(emails);
        this.setOffice(office);
        this.setIcon(icon);
        this.setTagIds(tagIds);
        this.setLinkIds(linkIds);
    }
    
    // START  ID ---------------------------------------------------------------
    private LongProperty idProperty;
    private long _id = DEFAULT_ID;

    @Id
    @Column(name = EMPLOYEER__COLUMN_NAME__ID)
    public long getId() {
        if (idProperty == null) {
            return _id;
        } else {
            return idProperty.get();
        }
    }

    public final void setId(final long id) {
        if (idProperty == null) {
            _id = id;
        } else {
            idProperty.set(id);
        }
    }

    public LongProperty idProperty() {
        if (idProperty == null) {
            idProperty = new SimpleLongProperty(this, EMPLOYEER__COLUMN_NAME__ID, _id);
        }
        
        return idProperty;
    }
    // END  ID -----------------------------------------------------------------
    
    // START  GENERATION-TIME --------------------------------------------------
    private LongProperty generationTimeProperty;
    private long _generationTime = DEFAULT_ID;

    @Id
    @Column(name = EMPLOYEER__COLUMN_NAME__GENERATION_TIME)
    public long getGenerationTime() {
        if (generationTimeProperty == null) {
            return _generationTime;
        } else {
            return generationTimeProperty.get();
        }
    }

    public final void setGenerationTime(final long generationTime) {
        if (generationTimeProperty == null) {
            _generationTime = generationTime;
        } else {
            generationTimeProperty.set(generationTime);
        }
    }

    public LongProperty generationTimeProperty() {
        if (generationTimeProperty == null) {
            generationTimeProperty = new SimpleLongProperty(this, EMPLOYEER__COLUMN_NAME__GENERATION_TIME, _generationTime);
        }
        
        return generationTimeProperty;
    }
    // END  GENERATION-TIME ----------------------------------------------------
    
    // START  FIRST-NAME -------------------------------------------------------
    private StringProperty firstNameProperty;
    private String _firstName = DEFAULT_STRING_EMPTY;
    
    @Column(name = EMPLOYEER__COLUMN_NAME__FIRST_NAME)
    public String getFirstName() {
        if (firstNameProperty == null) {
            return _firstName;
        } else {
            return firstNameProperty.get();
        }
    }
    
    public void setFirstName(final String firstName) {
        if (firstNameProperty == null) {
            _firstName = firstName;
        } else {
            firstNameProperty.set(firstName);
        }
    }
    
    public StringProperty firstNameProperty() {
        if (firstNameProperty == null) {
            firstNameProperty = new SimpleStringProperty(this, EMPLOYEER__COLUMN_NAME__FIRST_NAME, _firstName);
        }
        
        return firstNameProperty;
    }
    // END  FIRST-NAME ---------------------------------------------------------
    
    // START  SECOND-NAME ------------------------------------------------------
    private StringProperty secondNameProperty;
    private String _secondName = DEFAULT_STRING_EMPTY;
    
    @Column(name = EMPLOYEER__COLUMN_NAME__SECOND_NAME)
    public String getSecondName() {
        if (secondNameProperty == null) {
            return _secondName;
        } else {
            return secondNameProperty.get();
        }
    }
    
    public void setSecondName(final String secondName) {
        if (secondNameProperty == null) {
            _secondName = secondName;
        } else {
            secondNameProperty.set(secondName);
        }
    }
    
    public StringProperty secondNameProperty() {
        if (secondNameProperty == null) {
            secondNameProperty = new SimpleStringProperty(this, EMPLOYEER__COLUMN_NAME__SECOND_NAME, _secondName);
        }
        
        return secondNameProperty;
    }
    // END  SECOND-NAME --------------------------------------------------------
    
    // START  LAST-NAME --------------------------------------------------------
    private StringProperty lastNameProperty;
    private String _lastName = DEFAULT_STRING_EMPTY;
    
    @Column(name = EMPLOYEER__COLUMN_NAME__SECOND_NAME)
    public String getLastName() {
        if (lastNameProperty == null) {
            return _lastName;
        } else {
            return lastNameProperty.get();
        }
    }
    
    public void setLastName(final String lastName) {
        if (lastNameProperty == null) {
            _lastName = lastName;
        } else {
            lastNameProperty.set(lastName);
        }
    }
    
    public StringProperty lastNameProperty() {
        if (lastNameProperty == null) {
            lastNameProperty = new SimpleStringProperty(this, EMPLOYEER__COLUMN_NAME__SECOND_NAME, _lastName);
        }
        
        return lastNameProperty;
    }
    // END  LAST-NAME ----------------------------------------------------------
    
    // START  TITLES -----------------------------------------------------------
    private ListProperty<String>  titlesProperty;
    private final ArrayList<String> _titles      = new ArrayList();
    
    @Column(name = EMPLOYEER__COLUMN_NAME__TITLES)
    public ArrayList<String> getTitles() {
        if (titlesProperty == null) {
            return _titles;
        } else {
            return new ArrayList<>(titlesProperty.get());
        }
    }
    
    public void setTitles(final ArrayList<String> titles) {
        if (titlesProperty == null) {
            _titles.clear();
            _titles.addAll(titles);
        } else {
            titlesProperty.setAll(titles);
        }
    }
    
    public ListProperty<String> titlesProperty() {
        if (titlesProperty == null) {
            titlesProperty = new SimpleListProperty(this, EMPLOYEER__COLUMN_NAME__TITLES, 
                    FXCollections.observableArrayList(_titles));
        }
        
        return titlesProperty;
    }
    // END  TITLES -------------------------------------------------------------
    
    // START  INDEX ------------------------------------------------------------
    private IntegerProperty indexProperty;
    private int _index = DEFAULT_INDEX;
    
    @Column(name = EMPLOYEER__COLUMN_NAME__INDEX)
    public int getIndex() {
        if (indexProperty == null) {
            return _index;
        } else {
            return indexProperty.get();
        }
    }
    
    public void setIndex(final int index) {
        if (indexProperty == null) {
            _index = index;
        } else {
            indexProperty.set(index);
        }
    }
    
    public IntegerProperty indexProperty() {
        if (indexProperty == null) {
            indexProperty = new SimpleIntegerProperty(this, EMPLOYEER__COLUMN_NAME__INDEX, _index);
        }
        
        return indexProperty;
    }
    // END  INDEX --------------------------------------------------------------
    
    // START  FAVORITE ---------------------------------------------------------
    private BooleanProperty favoriteProperty;
    private boolean _favorite = DEFAULT_BOOLEAN;
    
    @Column(name = EMPLOYEER__COLUMN_NAME__FAVORITE)
    public boolean getFavorite() {
        if (favoriteProperty == null) {
            return _favorite;
        } else {
            return favoriteProperty.get();
        }
    }
    
    public void setFavorite(final boolean favorite) {
        if (favoriteProperty == null) {
            _favorite = favorite;
        } else {
            favoriteProperty.set(favorite);
        }
    }
    
    public BooleanProperty favoriteProperty() {
        if (favoriteProperty == null) {
            favoriteProperty = new SimpleBooleanProperty(this, EMPLOYEER__COLUMN_NAME__FAVORITE, _favorite);
        }
        
        return favoriteProperty;
    }
    // END  FAVORITE -----------------------------------------------------------
    
    // START  RESPONSIBILITIES -------------------------------------------------
    private ListProperty<String> responsibilitiesProperty;
    private final ArrayList<String> _responsibilities = new ArrayList();
    
    @Column(name = EMPLOYEER__COLUMN_NAME__RESPONSIBILITIES)
    public ArrayList<String> getResponsibilities() {
        if (responsibilitiesProperty == null) {
            return _responsibilities;
        } else {
            return new ArrayList<>(responsibilitiesProperty.get());
        }
    }
    
    public void setResponsibilities(final ArrayList<String> responsibilities) {
        if (responsibilitiesProperty == null) {
            _responsibilities.clear();
            _responsibilities.addAll(responsibilities);
        } else {
            responsibilitiesProperty.setAll(responsibilities);
        }
    }
    
    public ListProperty<String> responsibilitiesProperty() {
        if (responsibilitiesProperty == null) {
            responsibilitiesProperty = new SimpleListProperty(this, EMPLOYEER__COLUMN_NAME__RESPONSIBILITIES, 
                    FXCollections.observableArrayList(_responsibilities));
        }
        
        return responsibilitiesProperty;
    }
    // END  RESONSIBILITIES ----------------------------------------------------
    
    // START  PHONES -----------------------------------------------------------
    private ListProperty<String> phonesProperty;
    private final ArrayList<String> _phones = new ArrayList();
    
    @Column(name = EMPLOYEER__COLUMN_NAME__PHONES)
    public ArrayList<String> getPhones() {
        if (phonesProperty == null) {
            return _phones;
        } else {
            return new ArrayList<>(phonesProperty.get());
        }
    }
    
    public void setPhones(final ArrayList<String> phones) {
        if (phonesProperty == null) {
            _phones.clear();
            _phones.addAll(phones);
        } else {
            phonesProperty.setAll(phones);
        }
    }
    
    public ListProperty<String> phonesProperty() {
        if (phonesProperty == null) {
            phonesProperty = new SimpleListProperty(this, EMPLOYEER__COLUMN_NAME__PHONES, 
                    FXCollections.observableArrayList(_phones));
        }
        
        return phonesProperty;
    }
    // END  PHONES -------------------------------------------------------------
    
    // START  EMAILS -----------------------------------------------------------
    private ListProperty<String> emailsProperty;
    private final ArrayList<String> _emails = new ArrayList();
    
    @Column(name = EMPLOYEER__COLUMN_NAME__EMAILS)
    public ArrayList<String> getEMails() {
        if (emailsProperty == null) {
            return _emails;
        } else {
            return new ArrayList<>(emailsProperty.get());
        }
    }
    
    public void setEMails(final ArrayList<String> emails) {
        if (emailsProperty == null) {
            _emails.clear();
            _emails.addAll(emails);
        } else {
            emailsProperty.setAll(emails);
        }
    }
    
    public ListProperty<String> emailsProperty() {
        if (emailsProperty == null) {
            emailsProperty = new SimpleListProperty(this, EMPLOYEER__COLUMN_NAME__EMAILS, 
                    FXCollections.observableArrayList(_emails));
        }
        
        return emailsProperty;
    }
    // END  EMAILS -------------------------------------------------------------
    
    // START  OFFICE -----------------------------------------------------------
    private StringProperty officeProperty;
    private String _office = DEFAULT_STRING_EMPTY;
    
    @Column(name = EMPLOYEER__COLUMN_NAME__OFFICE)
    public String getOffice() {
        if (officeProperty == null) {
            return _office;
        } else {
            return officeProperty.get();
        }
    }
    
    public void setOffice(final String office) {
        if (officeProperty == null) {
            _office = office;
        } else {
            officeProperty.set(office);
        }
    }
    
    public StringProperty officeProperty() {
        if (officeProperty == null) {
            officeProperty = new SimpleStringProperty(this, EMPLOYEER__COLUMN_NAME__OFFICE, _office);
        }
        
        return officeProperty;
    }
    // END  OFFICE -------------------------------------------------------------
    
    // START  ICON -------------------------------------------------------------
    private StringProperty iconProperty;
    private String _icon = DEFAULT_STRING_EMPTY;
    
    @Column(name = EMPLOYEER__COLUMN_NAME__ICON)
    public String getIcon() {
        if (iconProperty == null) {
            return _icon;
        } else {
            return iconProperty.get();
        }
    }
    
    public void setIcon(final String icon) {
        if (iconProperty == null) {
            _icon = icon;
        } else {
            iconProperty.set(icon);
        }
    }
    
    public StringProperty iconProperty() {
        if (iconProperty == null) {
            iconProperty = new SimpleStringProperty(this, EMPLOYEER__COLUMN_NAME__ICON, _icon);
        }
        
        return iconProperty;
    }
    // END  ICON ---------------------------------------------------------------
    
    // START  TAGS-IDS ---------------------------------------------------------
    private ListProperty<Long> tagsIdsProperty;
    private final ArrayList<Long> _tagsIds = new ArrayList();
    
    @Column(name = EMPLOYEER__COLUMN_NAME__TAG_IDS)
    public ArrayList<Long> getTagIds() {
        if (tagsIdsProperty == null) {
            return _tagsIds;
        } else {
            return new ArrayList<>(tagsIdsProperty.get());
        }
    }
    
    public void setTagIds(final ArrayList<Long> tagsIds) {
        if (tagsIdsProperty == null) {
            _tagsIds.clear();
            _tagsIds.addAll(tagsIds);
        } else {
            tagsIdsProperty.setAll(tagsIds);
        }
    }
    
    public ListProperty<Long> tagsIdsProperty() {
        if (tagsIdsProperty == null) {
            tagsIdsProperty = new SimpleListProperty(this, EMPLOYEER__COLUMN_NAME__TAG_IDS,
                    FXCollections.observableArrayList(_tagsIds));
        }
        
        return tagsIdsProperty;
    }
    // END  TAGS-IDS -----------------------------------------------------------
    
    // START  LINKS-IDS --------------------------------------------------------
    private ListProperty<Long> linkIdsProperty;
    private final ArrayList<Long> _linkIds = new ArrayList();
    
    @Column(name = EMPLOYEER__COLUMN_NAME__LINK_IDS)
    public ArrayList<Long> getLinkIds() {
        if (linkIdsProperty == null) {
            return _linkIds;
        } else {
            return new ArrayList<>(linkIdsProperty.get());
        }
    }
    
    public void setLinkIds(final ArrayList<Long> linkIds) {
        if (linkIdsProperty == null) {
            _linkIds.clear();
            _linkIds.addAll(linkIds);
        } else {
            linkIdsProperty.setAll(linkIds);
        }
    }
    
    public ListProperty<Long> linksIdsProperty() {
        if (linkIdsProperty == null) {
            linkIdsProperty = new SimpleListProperty(this, EMPLOYEER__COLUMN_NAME__LINK_IDS,
                    FXCollections.observableArrayList(_linkIds));
        }
        
        return linkIdsProperty;
    }
    // END  LINKS-IDS ----------------------------------------------------------
    
    @Override
    public int compareTo(final Employeer other) {
        return new CompareToBuilder()
                .append(this.getIndex(),          other.getIndex())
                .append(this.getId(),             other.getId())
                .append(this.getGenerationTime(), other.getGenerationTime())
                .append(this.getLastName(),       other.getLastName())
                .append(this.getFirstName(),      other.getFirstName())
                .append(this.getSecondName(),     other.getSecondName())
                .toComparison();
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        
        if (
                obj == null
                || this.getClass() != obj.getClass()
	) {
            return false;
        }
        
        final Employeer other = (Employeer) obj;
        return new EqualsBuilder()
                .append(this.getId(),             other.getId())
                .append(this.getGenerationTime(), other.getGenerationTime())
                .append(this.getLastName(),       other.getLastName())
                .append(this.getFirstName(),      other.getFirstName())
                .append(this.getSecondName(),     other.getSecondName())
                .append(this.getIndex(),          other.getIndex())
                .append(this.getFavorite(),       other.getFavorite())
                .isEquals();
    }
    
    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(this.getId())
                .append(this.getGenerationTime())
                .append(this.getLastName())
                .append(this.getFirstName())
                .append(this.getSecondName())
                .append(this.getIndex())
                .append(this.getFavorite())
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append(EMPLOYEER__COLUMN_NAME__ID,              this.getId())
                .append(EMPLOYEER__COLUMN_NAME__GENERATION_TIME, this.getGenerationTime())
                .append(EMPLOYEER__COLUMN_NAME__LAST_NAME,       this.getLastName())
                .append(EMPLOYEER__COLUMN_NAME__FIRST_NAME,      this.getFirstName())
                .append(EMPLOYEER__COLUMN_NAME__SECOND_NAME,     this.getSecondName())
                .append(EMPLOYEER__COLUMN_NAME__INDEX,           this.getIndex())
                .append(EMPLOYEER__COLUMN_NAME__FAVORITE,        this.getFavorite())
                .append(EMPLOYEER__COLUMN_NAME__OFFICE,          this.getOffice())
                .append(EMPLOYEER__COLUMN_NAME__ICON,            this.getIcon())
                .toString();
    }
    
    /*
        this.setId(id);
        this.setGenerationTime(generationTime);
        this.setFirstName(firstName);
        this.setSecondName(secondName);
        this.setLastName(lastName);
        this.setTitles(titles);
        this.setIndex(index);
        this.setFavorite(favorite);
        this.setResponsibilities(responsibilities);
        this.setPhones(phones);
        this.setEMails(emails);
        this.setOffice(office);
        this.setIcon(icon);
        this.setTagIds(tagIds);
        this.setLinkIds(linkIds);
    */
    
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeLong(   this.getId());
        out.writeLong(   this.getGenerationTime());
        out.writeObject( this.getLastName());
        out.writeObject( this.getFirstName());
        out.writeObject( this.getSecondName());
        out.writeObject( this.getTitles());
        out.writeInt(    this.getIndex());
        out.writeBoolean(this.getFavorite());
        out.writeObject( this.getResponsibilities());
        out.writeObject( this.getPhones());
        out.writeObject( this.getEMails());
        out.writeObject( this.getOffice());
        out.writeObject( this.getIcon());
        out.writeObject( this.getTagIds());
        out.writeObject( this.getLinkIds());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.setId(in.readLong());
        this.setGenerationTime(in.readLong());
        this.setLastName(String.valueOf(in.readObject()));
        this.setFirstName(String.valueOf(in.readObject()));
        this.setSecondName(String.valueOf(in.readObject()));
        this.setTitles((ArrayList<String>)in.readObject());
        this.setIndex(in.readInt());
        this.setFavorite(in.readBoolean());
        this.setResponsibilities((ArrayList<String>)in.readObject());
        this.setPhones((ArrayList<String>)in.readObject());
        this.setEMails((ArrayList<String>)in.readObject());
        this.setOffice(String.valueOf(in.readObject()));
        this.setIcon(String.valueOf(in.readObject()));
        this.setTagIds((ArrayList<Long>)in.readObject());
        this.setLinkIds((ArrayList<Long>)in.readObject());
    }
    
}
